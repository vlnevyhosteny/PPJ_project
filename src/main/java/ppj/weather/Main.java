package ppj.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import ppj.weather.configs.AppConfiguration;
import ppj.weather.provisioning.Provisioner;

@EnableTransactionManagement
@EntityScan("ppj.weather.model")
@ComponentScan(basePackages = {"ppj.weather"})
public class Main {

    @Profile({"devel"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    @Autowired
    private static StateDao stateDao;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);

        //System.out.println(stateDao.getStates().size());

        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);

    }
}

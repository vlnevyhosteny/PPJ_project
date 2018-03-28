package ppj.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ppj.weather.configs.AppConfiguration;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        app.setAdditionalProfiles("devel");

        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);

    }
}

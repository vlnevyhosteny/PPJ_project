package ppj.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;

import ppj.weather.configs.AppConfiguration;
import ppj.weather.model.CityDao;
import ppj.weather.model.StateDao;
import ppj.weather.provisioning.Provisioner;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("ppj.weather.model")
@ComponentScan(basePackages = {"ppj.weather"})
public class Main {

    @Bean
    public StateDao stateDao() {
        return new StateDao();
    }

    @Bean
    public CityDao cityDao() {
        return new CityDao();
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    @Profile({"devel"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    @Autowired
    private static StateDao stateDao;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        
        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);

    }
}

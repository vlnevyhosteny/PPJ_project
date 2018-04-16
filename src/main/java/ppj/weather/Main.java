package ppj.weather;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ppj.weather.model.CityDao;
import ppj.weather.model.StateDao;
import ppj.weather.provisioning.Provisioner;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("ppj.weather.model")
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
    public EntityManagerFactory entityManagerFactory;

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    @Profile({"devel"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        
        ApplicationContext ctx = app.run(args);

    }
}

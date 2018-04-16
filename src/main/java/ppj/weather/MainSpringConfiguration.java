package ppj.weather;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ppj.weather.configs.AppConfiguration;
import ppj.weather.configs.ProvisionerConfig;

@Configuration
@Import({
        AppConfiguration.class,
        ProvisionerConfig.class
})
@ComponentScan("ppj.weather")
public class MainSpringConfiguration {

}

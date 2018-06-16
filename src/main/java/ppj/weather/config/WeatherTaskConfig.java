package ppj.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Conditional(ReadOnlyModeConfig.ReadOnlyModeDisabled.class)
@Configuration
public class WeatherTaskConfig {

    @Bean
    ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}

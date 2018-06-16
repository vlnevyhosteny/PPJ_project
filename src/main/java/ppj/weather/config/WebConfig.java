package ppj.weather.config;

import org.assertj.core.internal.Conditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ppj.weather.Main;
import ppj.weather.web.OnlyGetMethodInterceptor;


@Conditional(ReadOnlyModeConfig.ReadOnlyModeEnabled.class)
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public WebConfig() {
        log.info("Read only mode is enabled.");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OnlyGetMethodInterceptor());
    }

}

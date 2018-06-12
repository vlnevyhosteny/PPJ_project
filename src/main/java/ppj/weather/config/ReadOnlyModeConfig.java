package ppj.weather.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class ReadOnlyModeConfig {

/*    public static class ReadOnlyModeEnabled implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String property = conditionContext.getEnvironment().getProperty("app.read-only-mode");
            return property.equals("true");
        }
    }

    public static class ReadOnlyModeDisabled implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String property = conditionContext.getEnvironment().getProperty("app.read-only-mode");
            return !property.equals("true");
        }
    }*/

}

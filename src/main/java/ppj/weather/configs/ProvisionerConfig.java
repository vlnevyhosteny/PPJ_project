package ppj.weather.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "provisioner")
public class ProvisionerConfig {

    @NotNull
    private String createScriptClassPath;

    public String getCreateScriptClassPath() {
        return createScriptClassPath;
    }

    public void setCreateScriptClassPath(String createScriptClassPath) {
        this.createScriptClassPath = createScriptClassPath;
    }
}

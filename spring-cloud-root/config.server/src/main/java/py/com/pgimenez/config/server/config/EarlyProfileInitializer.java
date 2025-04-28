package py.com.pgimenez.config.server.config;

import org.springframework.core.env.ConfigurableEnvironment;

import py.com.pgimenez.config.server.util.DotEnvLoader;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class EarlyProfileInitializer implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();
        String activeProfile = env.getActiveProfiles().length > 0 
            ? env.getActiveProfiles()[0]
            : "default";
        new DotEnvLoader(activeProfile)
            .loadIntoSystemProperties();
    }

}

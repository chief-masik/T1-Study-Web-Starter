package org.pyatakov.t1studywebstarter;

import org.springframework.boot.DefaultPropertiesPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

public class WebEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final Map<String, Object> PROPERTIES = new HashMap<>();

    static {
        PROPERTIES.put("logging.level.org.zalando.logbook.Logbook", "trace");
        PROPERTIES.put("logbook.format.style", "splunk");
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        DefaultPropertiesPropertySource.addOrMerge(PROPERTIES, environment.getPropertySources());
    }
}

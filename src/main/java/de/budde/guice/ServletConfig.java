package de.budde.guice;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ServletConfig extends GuiceServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ServletConfig.class);

    private Injector injector;

    private final Properties allProperties;

    public ServletConfig(Properties allProperties) {
        this.allProperties = allProperties;
    }

    @Override
    protected Injector getInjector() {
        JerseyServletModule jerseyServletModule = new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                GuiceModule guiceModule = new GuiceModule(ServletConfig.this.allProperties);
                install(guiceModule);
                Map<String, String> initParams = new HashMap<>();
                // initParams.put("com.sun.jersey.config.feature.Trace", "true");
                initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                String commaSeparatedPackages = "de.budde.resources,de.budde.provider";
                initParams.put("com.sun.jersey.config.property.packages", commaSeparatedPackages);
                serve("/*").with(GuiceContainer.class, initParams);
            }
        };
        this.injector = Guice.createInjector(jerseyServletModule);
        return this.injector;
    }

    /**
     * return the created injector. Only the ServerStarter or tests should use this method. It is not dangerous, but why should you use it?<br>
     * <b>Somebody else must have called getInjector() before. Otherwise null is returned.</b>
     *
     * @return the created injector. Null, if the injector has not yet been created.
     */
    public Injector getCreatedInjector() {
        LOG.info("guice injector is accessed");
        return this.injector;
    }
}

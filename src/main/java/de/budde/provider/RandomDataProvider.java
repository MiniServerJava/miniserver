package de.budde.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import de.budde.param.RandomRequest;

@Provider
public class RandomDataProvider implements InjectableProvider<RandomData, Parameter> {
    private static final Logger LOG = LoggerFactory.getLogger(RandomDataProvider.class);

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public Injectable<?> getInjectable(ComponentContext ic, RandomData a, Parameter p) {
        if ( RandomRequest.class.isAssignableFrom(p.getParameterClass()) ) {
            return getInjectableRandomRequest();
        } else {
            return null;
        }
    }

    private Injectable<RandomRequest> getInjectableRandomRequest() {
        return () -> {
            String entity = null;
            try {
                entity = convertStreamToString(RandomDataProvider.this.servletRequest.getInputStream());
                return RandomRequest.make_1(entity);
            } catch ( IOException e ) {
                LOG.error("RandomRequest entity could not be parsed: " + entity, e);
                return null;
            }
        };
    }

    static String convertStreamToString(InputStream is) {
        try (Scanner s = new Scanner(is)) {
            s.useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }
}
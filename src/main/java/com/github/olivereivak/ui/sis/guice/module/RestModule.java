package com.github.olivereivak.ui.sis.guice.module;

import com.github.olivereivak.ui.sis.config.ApplicationConfig;
import com.github.olivereivak.ui.sis.resource.filter.ApplicationPersistFilter;
import com.github.olivereivak.ui.sis.config.ApplicationWebConfig;
import com.google.inject.Provides;

public class RestModule extends org.gwizard.rest.RestModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        filter("/*").through(ApplicationPersistFilter.class);
    }

    @Provides
    public ApplicationWebConfig applicationWebConfig(ApplicationConfig cfg) {
        return cfg.getWeb();
    }

}

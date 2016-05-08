package com.github.olivereivak.ui.sis.guice.module;

import org.gwizard.hibernate.DatabaseConfig;
import org.gwizard.logging.LoggingConfig;
import org.gwizard.web.WebServer;

import com.github.olivereivak.ui.sis.config.ApplicationConfig;
import com.github.olivereivak.ui.sis.guice.provider.ObjectMapperProvider;
import com.github.olivereivak.ui.sis.resource.BaseResource;
import com.github.olivereivak.ui.sis.services.ShutdownService;
import com.github.olivereivak.ui.sis.util.ReflectionUtils;
import com.github.olivereivak.ui.sis.web.ApplicationWebServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * Consider using Reflections to do this automatically.</p>
 *
 * <p>We must provide bindings for the LoggingConfig, WebConfig, and DatabaseConfig to use the
 * logging, rest, and hibernate modules.</p>
 */
@Slf4j
public class ApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		bindResources();

		bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);

		bind(WebServer.class).to(ApplicationWebServer.class);
		bind(ShutdownService.class).asEagerSingleton();
	}

	private void bindResources() {
		for (Class resource : ReflectionUtils.getClasses("com.github.olivereivak.ui.sis.resource", BaseResource.class)) {
			log.debug("Binding resource " + resource.getSimpleName());
			bind(resource);
		}
	}

	@Provides
	public LoggingConfig loggingConfig(ApplicationConfig cfg) {
		return cfg.getLogging();
	}

	@Provides
	public DatabaseConfig databaseConfig(ApplicationConfig cfg) {
		return cfg.getDatabase();
	}
}

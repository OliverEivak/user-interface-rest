package com.github.olivereivak.ui.sis.guice.module;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;

import javax.inject.Singleton;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;

import org.jboss.resteasy.specimpl.ResteasyHttpHeaders;

import com.github.olivereivak.ui.sis.config.ApplicationConfig;
import com.github.olivereivak.ui.sis.test.FullWebStackTestBase;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		// Managing servlet stuff in a test environment can sometimes be tricky, but basically just bind what
		// you need. If you end up needing request scope, you bind the scope yourself.
		bind(HttpHeaders.class).toInstance(new ResteasyHttpHeaders(new MultivaluedHashMap<String, String>()));
	}

	@Provides
	@Singleton
	public ApplicationConfig exampleConfig() {
		ApplicationConfig cfg = new ApplicationConfig();

		cfg.setShutdownPort(11666);

		cfg.getWeb().setPort(FullWebStackTestBase.TEST_PORT);
		cfg.getWeb().getInitParameters().put("resteasy.role.based.security", "true");
		cfg.getWeb().getInitParameters().put("resteasy.providers", "com.github.olivereivak.ui.sis.resource.filter.AuthenticationFilter,"
				+ "com.github.olivereivak.ui.sis.resource.exception.ApplicationExceptionMapper");

		cfg.getDatabase().setDriverClass("org.h2.Driver");
		cfg.getDatabase().setUser("sa");
		cfg.getDatabase().setUrl("jdbc:h2:mem:test;MODE=MySQL;DATABASE_TO_UPPER=false");
		cfg.getDatabase().getProperties().put(DIALECT, "org.hibernate.dialect.H2Dialect");
		cfg.getDatabase().getProperties().put(HBM2DDL_AUTO, "create");

		cfg.getLogging().getLoggers().put("com.github.olivereivak.ui.sis", Level.TRACE);

		return cfg;
	}
}

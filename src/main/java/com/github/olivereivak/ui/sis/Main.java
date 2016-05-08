package com.github.olivereivak.ui.sis;

import java.io.File;

import org.gwizard.config.ConfigModule;
import org.gwizard.hibernate.HibernateModule;
import org.gwizard.logging.LoggingModule;
import org.gwizard.services.Run;

import com.github.olivereivak.ui.sis.config.ApplicationConfig;
import com.github.olivereivak.ui.sis.guice.GuiceInjector;
import com.github.olivereivak.ui.sis.guice.module.ApplicationModule;
import com.github.olivereivak.ui.sis.guice.module.RestModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static String[] arguments;

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("First argument needs to be a yaml config file, doofus");
			return;
		}

		arguments = args;

		Injector injector = Guice.createInjector(
				new ApplicationModule(),
				new ConfigModule(new File(args[0]), ApplicationConfig.class),
				new LoggingModule(),
				new RestModule(),
				new HibernateModule());

		injector.getInstance(Run.class).start();
		GuiceInjector.setInjector(injector);
	}

}

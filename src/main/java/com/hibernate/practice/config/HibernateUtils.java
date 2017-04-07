package com.hibernate.practice.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	public static final SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();

			// Configuration configuration = new Configuration()
			// .configure(HibernateUtils.class.getResource("/hibernate.cfg.xml"));
			// StandardServiceRegistryBuilder serviceRegistryBuilder = new
			// StandardServiceRegistryBuilder();
			// serviceRegistryBuilder.applySettings(configuration.getProperties());
			// ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			// sessionFactory =
			// configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			System.err.println("Unable to create the session factory");
			throw new ExceptionInInitializerError(ex);
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}

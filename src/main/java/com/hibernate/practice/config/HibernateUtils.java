package com.hibernate.practice.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

  public static final SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration()
          .configure(HibernateUtils.class.getResource("/hibernate-annotation.cfg.xml"));
      sessionFactory = configuration.buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Unable to create the session factory");
      ex.printStackTrace();
      throw new ExceptionInInitializerError(ex);
    }

  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void closeSessionFactory() {
    System.out.println("Closing the session factory");
    sessionFactory.close();
  }

}

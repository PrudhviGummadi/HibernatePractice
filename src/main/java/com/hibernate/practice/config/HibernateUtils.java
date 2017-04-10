package com.hibernate.practice.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.practice.model.Customer;
import com.hibernate.practice.model.Student;
import com.hibernate.practice.model.Transaction;

public class HibernateUtils {

  public static final SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration()
          .configure(HibernateUtils.class.getResource("/hibernate-annotation.cfg.xml"));
      configuration.addAnnotatedClass(Student.class);
      configuration.addAnnotatedClass(Transaction.class);
      configuration.addAnnotatedClass(Customer.class);
      StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
      serviceRegistryBuilder.applySettings(configuration.getProperties());
      ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    } catch (Throwable ex) {
      System.err.println("Unable to create the session factory");
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

package com.hibernate.practice;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.Department;
import com.hibernate.practice.model.University;

public class OneToManyUniDirectionalPractice {
  public static void main(String[] args) {
    try {

      University university = new University();
      university.setName("VIT University");

      Department dept1 = new Department("ECE Department", university);

      Session session = HibernateUtils.getSessionFactory().openSession();

      session.beginTransaction();

      System.out.println("Saving the dept1 details :" + dept1 + " in DB");
      // save without tx
      session.save(dept1);

      session.getTransaction().commit();


      System.out.println("Fetching the details from DB");
      dept1 = session.get(Department.class, dept1.getId());

      System.out.println("Details fetched from DB are: " + dept1);

      // removing the inserted data
      session.remove(dept1);

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }
}

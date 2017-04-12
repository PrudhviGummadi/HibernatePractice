package com.hibernate.practice;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.Department;
import com.hibernate.practice.model.University;

public class OneToManyBiDirectionalPractice {

  public static void main(String[] args) {
    try {
      University university = new University();
      university.setName("JNTU University");

      Department dept1 = new Department();
      dept1.setName("CS Department");
      dept1.setUniversity(university);

      Department dept2 = new Department();
      dept2.setName("ECE Department");
      dept2.setUniversity(university);

      Set<Department> departments = new HashSet<>();
      departments.add(dept1);
      departments.add(dept2);

      // Here this is the difference compared to uni-direction. We need to map on both side
      university.setDepartments(departments);

      // opening the session
      Session session = HibernateUtils.getSessionFactory().openSession();
      session.beginTransaction();

      // saving the entites in DB
      session.save(university);

      // Fetch the university data from DB
      dept1 = session.get(Department.class, dept1.getId());

      session.getTransaction().commit();

      // removing the inserted data
      session.remove(university);

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }

}

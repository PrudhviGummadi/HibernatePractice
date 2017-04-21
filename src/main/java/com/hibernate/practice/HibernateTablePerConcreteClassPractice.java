package com.hibernate.practice;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.ContractEmployee1;
import com.hibernate.practice.model.Employee;
import com.hibernate.practice.model.RegularEmployee1;

public class HibernateTablePerConcreteClassPractice {

  public static void main(String[] args) {
    try {

      Session session = HibernateUtils.getSessionFactory().openSession();

      Employee employee = new Employee();
      employee.setName("Sai Gummadi");

      RegularEmployee1 employee1 = new RegularEmployee1();
      employee1.setName("sai Kumar");
      employee1.setBonus(1000);
      employee1.setSalary(45000.5f);

      ContractEmployee1 employee2 = new ContractEmployee1();
      employee2.setName("Kumar Gummadi");
      employee2.setPayPerHour(70.5f);
      employee2.setContractDuration("2 months");

      // here all the above 3 objects are in transient state

      session.beginTransaction();

      session.persist(employee);

      int id = (Integer) session.save(employee1);

      session.persist(employee2);

      // here all the 3 objects are in persistent state.

      session.getTransaction().commit();

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }

}

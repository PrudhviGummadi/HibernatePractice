package com.hibernate.practice;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.ContractEmployee;
import com.hibernate.practice.model.Employee;
import com.hibernate.practice.model.RegularEmployee;

public class HibernateTablePerHierarchyPractice {

  public static void main(String[] args) {
    try {
      Session session = HibernateUtils.getSessionFactory().openSession();

      Employee employee = new Employee();
      employee.setName("Sai Gummadi");

      RegularEmployee regularEmployee = new RegularEmployee();
      regularEmployee.setName("Sai Kumar");
      regularEmployee.setBonus(1000);
      regularEmployee.setSalary(63000.00f);

      ContractEmployee contractEmployee = new ContractEmployee();
      contractEmployee.setName("Prudhvi Gummadi");
      contractEmployee.setPayPerHour(44.00f);
      contractEmployee.setContractDuration("15 hours");

      session.beginTransaction();

      session.persist(employee);

      session.persist(regularEmployee);

      session.persist(contractEmployee);

      session.getTransaction().commit();

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }

}

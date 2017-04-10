package com.hibernate.practice;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.dao.impl.HibernateSessionMethodDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernatSaveMethodPractice {

  public static void main(String[] args) {
    try {
      Student student = new Student("test_first", "test_last", "A");

      HibernateSessionMethodDAO studentDao = new HibernateSessionMethodDAOImpl();

      // Saving the student using the save method in session without
      // transaction
      System.out.println("Saving the student with given user details: " + student);
      studentDao.saveStudent(student);

      System.out.println(
          "******************************************************************************************************");

      Student student2 = new Student("test2", "test2_last", "B");
      System.out.println("Saving the student with given user details: " + student2);
      studentDao.saveStudentWithTransaction(student2);

      System.out.println(
          "******************************************************************************************************");

      // deleting the saved data
      // studentDao.removeStudent(id);
    } finally {
      HibernateUtils.closeSessionFactory();
    }

  }
}

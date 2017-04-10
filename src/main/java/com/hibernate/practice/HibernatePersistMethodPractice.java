package com.hibernate.practice;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.dao.impl.HibernateSessionMethodDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernatePersistMethodPractice {
	public static void main(String[] args) {
		try {
			Student student = new Student("first_persist", "last_persist", "A");

			HibernateSessionMethodDAO studentDao = new HibernateSessionMethodDAOImpl();

			// persisting the object in DB
			System.out.println("Student Details: " + student);
			int id = studentDao.persistStudent(student);

			System.out.println(
					"******************************************************************************************************");

			// Fetching the details from DB
			System.out.println("Fetching the Details from DB for student with id: " + id);
			studentDao.getStudent(id);

			System.out.println(
					"******************************************************************************************************");

			// removing the inserted data from DB
			studentDao.removeStudent(id);

		} finally {
			HibernateUtils.closeSessionFactory();
		}
	}
}

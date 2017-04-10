package com.hibernate.practice;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.dao.impl.HibernateSessionMethodDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernateGetOrLoadPractice {

	public static void main(String[] args) {
		try {
			Student student = new Student("first", "last", "A");

			HibernateSessionMethodDAO studentDao = new HibernateSessionMethodDAOImpl();

			int id = studentDao.saveStudentWithTransaction(student);

			// Here I am explicitly closing/opening the session to
			// verify the get or load examples because if the object
			// already existed in the session. I am unable to see the actual get
			// or load difference because they are fetching the object from
			// session.
			System.out.println("Calling the load method");
			studentDao.loadStudent(id);

			System.out.println("calling the get method");
			studentDao.getStudent(id);

			System.out.println("removing the inserted data");
			studentDao.removeStudent(id);

		} finally {
			HibernateUtils.closeSessionFactory();
		}
	}

}

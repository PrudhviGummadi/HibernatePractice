package com.hibernate.practice;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.dao.impl.HibernateSessionMethodDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernateUpdateOrMergePractice {
	public static void main(String[] args) {
		try {
			Student student = new Student("first", "last", "A");

			HibernateSessionMethodDAO studentDao = new HibernateSessionMethodDAOImpl();

			// saving the student with given details
			int id = studentDao.saveStudentWithTransaction(student);

			// testing the merge method in session.
			studentDao.mergeStudent(id);

			// studentDao.updateStudent(id);

			// removing the inserted data
			studentDao.removeStudent(id);

		} finally {
			HibernateUtils.closeSessionFactory();
		}
	}
}

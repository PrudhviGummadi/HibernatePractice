package com.hibernate.practice;

import org.junit.Assert;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.dao.impl.HibernateSessionMethodDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernateSaveOrUpdateMethodPractice {

	public static void main(String[] args) {

		try {

			// creating a new student who doesn't exist in DB
			Student student = new Student("first", "last", "A");

			HibernateSessionMethodDAO studentDao = new HibernateSessionMethodDAOImpl();

			// saving the new student entity in DB using SaveOrUpdate Method
			int id = studentDao.saveOrUpdate(student);

			// Fetching the student details from DB
			student = studentDao.getStudent(id);

			student.setSection("B");

			// calling the saveOrUpdate() method using already existing object
			// in DB. Check the sql statments to see the difference between
			// above and below. Here update query will be executed where as in
			// above scenario insert statement will be executed
			int id1 = studentDao.saveOrUpdate(student);

			Assert.assertEquals(id, id1);

			// Fetching the Student details from DB again
			studentDao.getStudent(id1);

			// removing the inserted date from DB
			studentDao.removeStudent(id1);

		} finally {
			HibernateUtils.closeSessionFactory();
		}
	}

}

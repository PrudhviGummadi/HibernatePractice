package com.hibernate.practice;

import com.hibernate.practice.dao.StudentDAO;
import com.hibernate.practice.dao.imp.StudentDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernatStandAlone {
	public static void main(String[] args) {
		StudentDAO studentDao = new StudentDAOImpl();

		// Saving one student
		Student student1 = new Student();
		student1.setFirstname("sai");
		student1.setLastname("gummadi");
		student1.setSection("A");
		System.out.println("Saving the student in the MYSQL DB: " + student1);
		int id = studentDao.saveStudent(student1);
		System.out.println("Student1 id: " + id);

	}
}

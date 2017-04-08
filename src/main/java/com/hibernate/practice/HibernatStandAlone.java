package com.hibernate.practice;

import java.util.List;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.StudentDAO;
import com.hibernate.practice.dao.impl.StudentDAOImpl;
import com.hibernate.practice.model.Student;

public class HibernatStandAlone {
	public static void main(String[] args) {

		try {
			StudentDAO studentDao = new StudentDAOImpl();

			// Saving one student
			Student student = new Student("test", "testLast", "D");
			Student student2 = new Student("test2", "testLast2", "B");
			Student student3 = new Student("test3", "testLast3", "C");

			System.out.println("Saving the student in the MYSQL DB: " + student2);
			int id = studentDao.saveStudent(student);
			int id2 = studentDao.saveStudent(student2);
			int id3 = studentDao.saveStudent(student3);
			System.out.println("Student1 id: " + id2);

			// Fetching all the students in the student table
			List<Student> students = studentDao.getAllStudents();
			students.stream().forEach(s -> System.out.println(s));

			// updating the student with update method in session
			student = studentDao.getStudentById(id);
			student.setSection("A");
			System.out.println("Updating the student user details: " + student);
			studentDao.updateStudent(student);

			// fetching the updated user details
			student = studentDao.getStudentById(id);
			System.out.println("Student after updated: " + student);

			// update the student with another method in interface with invoking
			// update method in session.
			studentDao.updateStudent(id, "C");

			// fetching the updated user details
			student = studentDao.getStudentById(id);
			System.out.println("Student after updated: " + student);

			Student student4 = new Student();
			student4.setId(id);
			student4.setFirstname("merged_user");
			student4.setLastname("merged_lastname");
			student4.setSection("D");
			studentDao.mergeStudent(id, student4);

			System.out.println("Student after the merge operation instead of update: " + student);

			// Delete all Students from student table with given firstName
			studentDao.deleteAllStudentsByFirstName("test2");

			// Delete a student based on id
			studentDao.deleteById(id3);

			// Just to remove the inserted data
			// studentDao.deleteAllStudentsByFirstName("test");
			studentDao.deleteAllStudentsByFirstName("test");
			studentDao.deleteAllStudentsByFirstName("test3");

			// Fetching the list of students after delete operations
			students = studentDao.getAllStudents();
			students.stream().forEach(s -> System.out.println(s));
		} finally {
			// closing the session
			HibernateUtils.closeSessionFactory();
		}
	}
}

package com.hibernate.practice.dao.imp;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.StudentDAO;
import com.hibernate.practice.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private Session session;

	@Override
	public int saveStudent(Student student) {
		session = HibernateUtils.getSessionFactory().openSession();
		System.out.println("Inside the save method");
		session.beginTransaction();
		int id = (Integer) session.save(student);
		session.getTransaction().commit();
		session.close();
		return id;

	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub

	}

}

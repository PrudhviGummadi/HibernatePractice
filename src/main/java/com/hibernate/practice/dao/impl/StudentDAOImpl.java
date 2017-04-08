package com.hibernate.practice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.StudentDAO;
import com.hibernate.practice.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private final Session session;

	public StudentDAOImpl() {
		session = HibernateUtils.getSessionFactory().openSession();
	}

	@Override
	public int saveStudent(Student student) {
		System.out.println("Inside the save method");
		session.beginTransaction();
		int id = (Integer) session.save(student);
		session.getTransaction().commit();
		return id;

	}

	@Override
	public List<Student> getAllStudents() {
		session.beginTransaction();
		Query query = session.createQuery("From Student s ORDER BY s.firstname ASC");
		List<Student> students = query.getResultList();
		session.getTransaction().commit();
		return students;
	}

	@Override
	public void deleteAllStudentsByFirstName(String firstname) {
		session.beginTransaction();
		System.out.println("Deleting all the users with firstname: " + firstname);
		Query query = session.createQuery("DELETE FROM Student s where s.firstname=:firstname");
		query.setParameter("firstname", firstname);
		query.executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public void updateStudent(Student student) {
		System.out.println("updating the exsiting user with given details: " + student);
		session.beginTransaction();
		session.update(student);
		session.getTransaction().commit();

	}

	@Override
	public void deleteById(int id) {
		session.beginTransaction();
		System.out.println("Deleting a student with id:" + id);
		Query query = session.createNativeQuery("delete from student where id=" + id);
		query.executeUpdate();
		session.getTransaction().commit();

	}

	@Override
	public void updateStudent(int id, String section) {
		System.out.println("updating the Section of user with given id:" + id);
		session.beginTransaction();
		Student student = session.load(Student.class, id);
		student.setSection(section);
		session.getTransaction().commit();
	}

	@Override
	public Student getStudentById(int id) {
		session.beginTransaction();
		Query query = session.createQuery("FROM Student s where s.id=:id");
		query.setParameter("id", new Integer(id));
		session.getTransaction().commit();
		return (Student) query.getSingleResult();
	}

	// At first look both update() and merge() methods seems similar because
	// both of them are used to convert the object which is in detached state
	// into persistence state, but the major difference between update and merge
	// is that update method cannot be used when the same object exists in the
	// session. Let’s look at those difference with simple example.
	@Override
	public void mergeStudent(int id, Student mergeStudent) {
		session.beginTransaction();
		Student student = session.get(Student.class, id);
		System.out.println("Before merging the student: " + student);
		// This will throwing org.hibernate.NonUniqueObjectException
		// session.update(mergeStudent);
		session.merge(mergeStudent);
		System.out.println("After merging the student: " + mergeStudent);
		session.getTransaction().commit();
	}

}

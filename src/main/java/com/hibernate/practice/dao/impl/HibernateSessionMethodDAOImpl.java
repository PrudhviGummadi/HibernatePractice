package com.hibernate.practice.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.model.Student;

public class HibernateSessionMethodDAOImpl implements HibernateSessionMethodDAO {

	private Session session;

	public HibernateSessionMethodDAOImpl() {
		session = HibernateUtils.getSessionFactory().openSession();
	}

	@Override
	public int saveStudent(Student student) {
		// Here in this use case object gets save without any issue but if it is
		// cascading and we are using this method out of transaction then we
		// need to explicitly to call the flush method to save the changes
		int id = (Integer) session.save(student);
		System.out.println("Saved the following student details: " + student);

		// changing the value immediately after save
		// Here updated changes will get persisted in DB
		student.setSection("B");
		student = (Student) session.createQuery("FROM Student s where s.id=:id").setParameter("id", id)
				.getSingleResult();

		System.out.println("Changes after update: " + student);

		return id;
	}

	@Override
	public int saveStudentWithTransaction(Student student) {
		session.beginTransaction();

		// Here in this use case object gets save without any issue but if it is
		// cascading and we are using this method within transaction So we don't
		// need to explicitly to call the flush method to save the changes
		int id = (Integer) session.save(student);
		System.out.println("Saved the following student details: " + student);

		// changing the value immediately after save
		// Here updated changes will get persisted in DB
		student.setSection("C");
		student = (Student) session.createQuery("FROM Student s where s.id=:id").setParameter("id", id)
				.getSingleResult();

		System.out.println("Changes after update: " + student);

		session.getTransaction().commit();

		return id;
	}

	// Save and persist methods are almost similar.Except that persist() method
	// cannot be used without transaction and persist method won't return any
	// ID like save().
	@Override
	public int persistStudent(Student student) {
		session.beginTransaction();

		// persisting the object.
		session.persist(student);

		System.out.println("Student id is: " + student.getId());

		// updating the object.This should automatically persist the changes to
		// cascading objects. Here there are no cascading scenarios, so the main
		// object gets updated in DB when transaction is commited. Similar to
		// save inside of transaction.

		student.setLastname("updated");

		session.getTransaction().commit();
		return student.getId();
	}

	@Override
	public int saveOrUpdate(Student student) {
		session.beginTransaction();

		// save or update method depends on whether the entity already exists in
		// DB.
		session.saveOrUpdate(student);

		session.getTransaction().commit();
		return student.getId();
	}

	@Override
	public void mergeStudent(int id) {
		session.beginTransaction();

		Student student = session.load(Student.class, id);
		System.out.println("Student details loaded from DB: " + student);
		student.setFirstname("updated");
		student.setSection("D");
		session.getTransaction().commit();

		session.beginTransaction();
		System.out.println("Student details before merge operation: " + student);
		Student student1 = (Student) session.merge(student);
		System.out.println("Student details got after the merge the returned student entity: " + student1);
		System.out.println("Student details got after the merge the passed student entity: " + student1);
		student.setSection("B");
		student1.setSection("C");
		System.out.println(student);
		System.out.println(student1);
		session.getTransaction().commit();

		System.out.println("Student details after transaction committed student1: " + student1);
		System.out.println("Student details after transaction committed student: " + student);
	}

	@Override
	public void updateStudent(int id) {

		System.out.println("Updating the details of student with id: " + id);

		Student student = getStudent(id);
		System.out.println("Student details in DB before update " + student);

		session.beginTransaction();
		System.out.println("*************************************************************");
		student.setSection("B");
		System.out.println("Updating the student with id: " + student.getId() + " with given details: " + student);
		session.update(student);

		System.out.println("*************************************************************");
		System.out.println("Before Transaction commited");
		session.getTransaction().commit();
	}

	@Override
	public void removeStudent(int id) {
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM Student s WHERE s.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public Student getStudent(int id) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("***********************************************");
		Student student = session.get(Student.class, id);
		System.out.println("***********************************************");
		System.out.println("Student details fetched from DB");
		System.out.println("student first name: " + student.getFirstname());
		System.out.println("***********************************************");
		session.getTransaction().commit();
		return student;
	}

	@Override
	public Student loadStudent(int id) {
		session.close();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("***********************************************");
		Student student = session.load(Student.class, id);
		System.out.println("***********************************************");
		System.out.println("student Details fetched from DB");
		System.out.println("student first name: " + student.getFirstname());
		System.out.println("***********************************************");
		session.getTransaction().commit();
		session.close();
		return student;
	}

}

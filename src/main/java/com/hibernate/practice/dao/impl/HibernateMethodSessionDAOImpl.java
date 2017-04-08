package com.hibernate.practice.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.dao.HibernateSessionMethodDAO;
import com.hibernate.practice.model.Student;

public class HibernateMethodSessionDAOImpl implements HibernateSessionMethodDAO {

	private final Session session;

	public HibernateMethodSessionDAOImpl() {
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
		// Here updated changes won't get persisted in DB
		student.setSection("B");
		student = (Student) session.createQuery("FROM Student s where s.id=:id").setParameter("id", id)
				.getSingleResult();

		// Here even if student value shown as changes in console but the change
		// wont get persisted in DB.
		System.out.println("Changes after update: " + student);

		return id;
	}

	@Override
	public int saveStudentWithTransaction(Student student) {
		session.beginTransaction();

		int id = (Integer) session.save(student);
		System.out.println("Saved the following student details: " + student);

		// changing the value immediately after save
		// Here updated changes won't get persisted in DB
		student.setSection("C");
		student = (Student) session.createQuery("FROM Student s where s.id=:id").setParameter("id", id)
				.getSingleResult();

		// Here even if student value shown as changes in console but the change
		// wont get persisted in DB.
		System.out.println("Changes after update: " + student);

		session.getTransaction().commit();

		return id;
	}

	@Override
	public int persistStudent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveOrUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mergeStudent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeStudent(int id) {
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM Student s WHERE s.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
	}

}

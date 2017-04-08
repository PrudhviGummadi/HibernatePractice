package com.hibernate.practice.dao;

import com.hibernate.practice.model.Student;

public interface HibernateSessionMethodDAO {

	/***
	 * As the method name suggests, hibernate save() can be used to save entity
	 * to database. We can invoke this method outside a transaction, that’s why
	 * I don’t like this method to save data. If we use this without transaction
	 * and we have cascading between entities, then only the primary entity gets
	 * saved unless we explicitly flush the session.
	 *
	 * @return
	 */
	public abstract int saveStudent(Student student);

	public abstract int saveStudentWithTransaction(Student student);

	public abstract int persistStudent();

	public abstract void saveOrUpdate();

	public abstract void mergeStudent();

	public abstract void updateStudent();

	public void removeStudent(int id);

}

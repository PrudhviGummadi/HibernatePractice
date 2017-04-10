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

	/***
	 * Here Save method will update the cascading the objects because we are
	 * using save wiithin transaction.
	 *
	 * @param student
	 * @return
	 */
	public abstract int saveStudentWithTransaction(Student student);

	/***
	 * This method is similar to save method within transaction. Persist method
	 * cannot be used outside the transaction. Persist() method wont return any
	 * id like save() method
	 *
	 * @param student
	 * @return
	 */
	public abstract int persistStudent(Student student);

	/***
	 * Hibernate saveOrUpdate results into insert or update queries based on the
	 * provided data. If the data is present in the database, update query is
	 * executed.
	 *
	 * We can use saveOrUpdate() without transaction also, but again you will
	 * face the issues with mapped objects not getting saved if session is not
	 * flushed.
	 *
	 * Hibernate saveOrUpdate adds the entity object to persistent context and
	 * track any further changes. Any further changes are saved at the time of
	 * committing transaction, like persist.
	 *
	 * Here I am using saveOrUpdate method within transaction even though there
	 * are no cascading cases here.
	 *
	 * @param student
	 * @return id
	 */
	public abstract int saveOrUpdate(Student student);

	/****
	 * Hibernate merge can be used to update existing values, however this
	 * method create a copy from the passed entity object and return it. At
	 * first look both update() and merge() methods seems similar because both
	 * of them are used to convert the object which is in detached state into
	 * persistence state, but the major difference between update and merge is
	 * that update method cannot be used when the same object exists in the
	 * session. Let’s look at those difference with simple example.
	 *
	 * @param id
	 */
	public abstract void mergeStudent(int id);

	/***
	 * Hibernate update should be used where we know that we are only updating
	 * the entity information. This operation adds the entity object to
	 * persistent context and further changes are tracked and saved when
	 * transaction is committed.
	 *
	 * @param id
	 */
	public abstract void updateStudent(int id);

	/***
	 * Delete the student with given id
	 *
	 * @param id
	 */
	public void removeStudent(int id);

	/***
	 * Eager loading. Hits the DB or cache and gets the object when
	 * session.get() method is called
	 *
	 * @param id
	 * @return
	 */
	public Student getStudent(int id);

	/****
	 * lazy loading. when session.load() method is called it returns the proxy
	 * object. It only hits DB or access cache to fetch actual properties when
	 * we are trying to access properties on proxy instance
	 *
	 * @param id
	 * @return
	 */
	public Student loadStudent(int id);

}

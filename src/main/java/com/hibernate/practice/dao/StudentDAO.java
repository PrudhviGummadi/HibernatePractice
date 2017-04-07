package com.hibernate.practice.dao;

import java.util.List;

import com.hibernate.practice.model.Student;

public interface StudentDAO {

	public abstract int saveStudent(Student student);

	public abstract List<Student> getAllStudents();

	public abstract Student getStudentById(int id);

	public abstract void deleteAllStudentsByFirstName(String firstname);

	public abstract void deleteById(int id);

	public abstract void updateStudent(Student student);

	public abstract void updateStudent(int id, String section);

}
package com.hibernate.practice.dao;

import java.util.List;

import com.hibernate.practice.model.Student;

public interface StudentDAO {

	public abstract int saveStudent(Student student);

	public abstract List<Student> getAllStudents();

	public abstract void deleteStudent(int id);

	public abstract void updateStudent(Student student);

}
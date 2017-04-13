package com.hibernate.practice;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.Author;
import com.hibernate.practice.model.Book;

public class HibernateManyToManyMappingPractice {
  public static void main(String[] args) {

    try {
      Session session = HibernateUtils.getSessionFactory().openSession();

      Author author = new Author();
      author.setName("Sai Gummadi");

      Author author1 = new Author();
      author1.setName("prudhvi Gummadi");

      Book book = new Book("Java Book", "Introduction to java");

      Set<Book> books = new HashSet<>();
      books.add(book);

      author.setBooks(books);
      author1.setBooks(books);

      session.beginTransaction();

      session.save(author);
      session.save(author1);

      session.getTransaction().commit();

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }
}

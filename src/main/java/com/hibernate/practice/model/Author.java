package com.hibernate.practice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private Set<Book> books;

  public Author() {

  }

  public Author(String name, Set<Book> books) {
    this.name = name;
    this.books = books;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "author_book", catalog = "hibernatepractice", joinColumns = {
      @JoinColumn(name = "author_id") }, inverseJoinColumns = { @JoinColumn(name = "book_id") })
  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "Author [id=" + id + ", name=" + name + ", books=" + books + "]";
  }

}

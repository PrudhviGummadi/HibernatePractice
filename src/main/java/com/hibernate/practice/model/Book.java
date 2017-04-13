package com.hibernate.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private String description;

  // private Set<Author> authors;

  /**
   *
   */
  public Book() {
    super();
  }

  /**
   * @param name
   * @param description
   */
  public Book(String name, String description) {
    super();
    this.name = name;
    this.description = description;
  }

  /**
   * @param name
   * @param description
   * @param authors
   */
  // public Book(String name, String desc, Set<Author> authors) {
  // super();
  // this.name = name;
  // this.desc = desc;
  // this.authors = authors;
  // }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id", nullable = false)
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

  @Column(name = "description", nullable = false)
  public String getDesc() {
    return description;
  }

  public void setDesc(String description) {
    this.description = description;
  }

  // @ManyToMany(cascade = CascadeType.ALL, mappedBy = "authors")
  // public Set<Author> getAuthors() {
  // return authors;
  // }
  //
  // public void setAuthors(Set<Author> authors) {
  // this.authors = authors;
  // }

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", desc=" + description + /* ", authors=" + authors + */"]";
  }

}

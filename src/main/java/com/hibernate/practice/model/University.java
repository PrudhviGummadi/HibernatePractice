package com.hibernate.practice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//UNI-DIRECTIONAL MAPPING

@Entity
@Table(name = "UNIVERSITY")
public class University implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  // For bi-directional mapping, we need to add the child class here.
  private Set<Department> departments;

  public University() {

  }

  /**
   * @param id
   * @param name
   */
  public University(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /**
   * @param id
   * @param name
   * @param departments
   */
  public University(int id, String name, Set<Department> departments) {
    super();
    this.id = id;
    this.name = name;
    this.departments = departments;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "university")
  public Set<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(Set<Department> departments) {
    this.departments = departments;
  }

  @Override
  public String toString() {
    return "University [id=" + id + ", name=" + name + "]";
  }

}

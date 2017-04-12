package com.hibernate.practice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private University university;

  public Department(){

  }

  /**
   * @param name
   * @param university
   */
  public Department(String name, University university) {
    super();
    this.name = name;
    this.university = university;
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

  @JoinColumn(name = "univ_id")
  @ManyToOne(cascade = CascadeType.ALL)
  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }

  @Override
  public String toString() {
    return "Department [id=" + id + ", name=" + name + ", university=" + university + "]";
  }

}

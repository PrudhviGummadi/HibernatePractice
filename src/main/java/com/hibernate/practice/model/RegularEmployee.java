package com.hibernate.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "reg_emp")
public class RegularEmployee extends Employee implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;


  private float salary;

  private int bonus;

  @Column(name = "salary")
  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }

  @Column(name = "bonus")
  public int getBonus() {
    return bonus;
  }

  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  @Override
  public String toString() {
    return "RegulaEmployee [salary=" + salary + ", bonus=" + bonus + "]";
  }

}

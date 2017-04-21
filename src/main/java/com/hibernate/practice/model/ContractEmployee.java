package com.hibernate.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "con_emp")
public class ContractEmployee extends Employee implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private float payPerHour;

  private String contractDuration;

  @Column(name = "pay_per_hour")
  public float getPayPerHour() {
    return payPerHour;
  }

  public void setPayPerHour(float payPerHour) {
    this.payPerHour = payPerHour;
  }

  @Column(name = "contract_period")
  public String getContractDuration() {
    return contractDuration;
  }

  public void setContractDuration(String contractDuration) {
    this.contractDuration = contractDuration;
  }

  @Override
  public String toString() {
    return "ContractEmployee [payPerHour=" + payPerHour + ", contractDuration=" + contractDuration + "]";
  }

}

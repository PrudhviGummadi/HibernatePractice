package com.hibernate.practice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private Date txDate;

  private float txTotal;

  private Customer customer;

  public Transaction() {

  }

  /**
   * @param id
   * @param txDate
   * @param txTotal
   */
  public Transaction(Date txDate, float txTotal, Customer customer) {
    super();
    this.txDate = txDate;
    this.txTotal = txTotal;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "txn_id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "txn_date", nullable = false)
  public Date getTxDate() {
    return txDate;
  }

  public void setTxDate(Date txDate) {
    this.txDate = txDate;
  }

  @Column(name = "tx_total", nullable = false)
  public float getTxTotal() {
    return txTotal;
  }

  public void setTxTotal(float txTotal) {
    this.txTotal = txTotal;
  }

  // Because of bi-direction relation we are having child entity reference (Customer class) in parent entity
  // (transaction class)
  @OneToOne(mappedBy = "txn")
  @Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return id + ", " + txTotal + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getAddress();
  }

}

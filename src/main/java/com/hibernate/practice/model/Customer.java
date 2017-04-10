package com.hibernate.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private String address;

  private String email;

  private Transaction txn;

  public Customer() {

  }

  /**
   * @param name
   * @param address
   * @param email
   * @param txn
   */
  public Customer(String name, String address, String email, Transaction txn) {
    super();
    this.name = name;
    this.address = address;
    this.email = email;
    this.txn = txn;
  }

  @Id
  @GeneratedValue(generator = "gen")
  @GenericGenerator(name = "gen", strategy = "foreign", parameters = { @Parameter(name = "property", value = "txn") })
  @Column(name = "txn_id", nullable = false, unique = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "cust_name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "cust_address", nullable = false)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "cust_email", nullable = true)
  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  @OneToOne
  @PrimaryKeyJoinColumn
  public Transaction getTxn() {
    return txn;
  }

  public void setTxn(Transaction txn) {
    this.txn = txn;
  }

}

package com.hibernate.practice;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.practice.config.HibernateUtils;
import com.hibernate.practice.model.Customer;
import com.hibernate.practice.model.Transaction;

public class HibernateOneToOnePractice {

  public static void main(String[] args) {
    try {

      Transaction txn = getTransaction();

      // Opening the session
      Session session = HibernateUtils.getSessionFactory().openSession();

      // start transaction
      session.beginTransaction();

      System.out.println("saving the following transaction details in DB: " + txn);
      // Saving the transaction details in DB
      int id = (int) session.save(txn);

      // transaction committed
      session.getTransaction().commit();

      session.close();

      // opening session again
      session = HibernateUtils.getSessionFactory().openSession();

      session.beginTransaction();

      // Fetch the details from DB. Using load because I know transaction exists for sure.
      txn = session.load(Transaction.class, id);

      System.out.println("Details fetched from DB: " + txn);

      session.getTransaction().commit();

      // remove the inserted data from DB
      session.beginTransaction();

      session.remove(txn);

      session.getTransaction().commit();

    } finally {
      HibernateUtils.closeSessionFactory();
    }
  }


  private static Transaction getTransaction() {
    Transaction txn = new Transaction();
    txn.setTxDate(new Date());
    txn.setTxTotal(200.5f);

    Customer cust = new Customer();
    cust.setAddress("USA");
    cust.setEmail("test@gmail.com");
    cust.setName("test cust");

    // Because of bi-directional relationship we need to set each sides
    txn.setCustomer(cust);
    cust.setTxn(txn);

    return txn;

  }

}

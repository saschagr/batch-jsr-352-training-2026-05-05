package de.mathema.batch.job;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

  @Id
  private Long customerId;

  private String firstName;

  private String lastName;

  private String city;


  public Customer(String customerId, String firstName, String lastName, String city) {
    this.customerId = Long.valueOf(customerId);
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
  }
  
  public Customer() {
  }
}

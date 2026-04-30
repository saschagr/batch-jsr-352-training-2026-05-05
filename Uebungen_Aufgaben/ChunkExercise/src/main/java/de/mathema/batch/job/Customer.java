package de.mathema.batch.job;

public class Customer {

  private String customerId;

  private String firstName;

  private String lastName;

  private String city;


  public Customer(String customerId, String firstName, String lastName, String email, String phoneNumber, String address, String city, String nationState, String zipCode) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}

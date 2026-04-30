package de.mathema.batch.job;

public class Customer {

  private final String customerId;
  private String firstName;
  private String lastName;
  private String city;
  private String entryDate;
  private String membership;

  public Customer(String customerId, String firstName, String lastName, String city, String entryDate) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.entryDate = entryDate;
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

  public String getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(String entryDate) {
    this.entryDate = entryDate;
  }

  public String getMembership() {
    return membership;
  }

  public void setMembership(String membership) {
    this.membership = membership;
  }

}

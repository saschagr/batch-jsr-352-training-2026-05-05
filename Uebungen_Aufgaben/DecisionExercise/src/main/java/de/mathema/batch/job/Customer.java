package de.mathema.batch.job;

public class Customer {

  private final String customerId;
  private String firstName;
  private String lastName;
  private String city;
  private String entryDate;

  private String membership;
  private String discountPercentage;

  public Customer(String customerId, String firstName, String lastName, String city, String entryDate) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.entryDate = entryDate;
  }

  public Customer(String customerId, String firstName, String lastName, String city, String entryDate, String membership, String discountPercentage) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.entryDate = entryDate;
    this.membership = membership;
    this.discountPercentage = discountPercentage;
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

  public String getEntryDate() {
    return entryDate;
  }

  public void setEntyDate(String entryDate) {
    this.entryDate = entryDate;
  }

  public String getMembership() {
    return membership;
  }

  public void setMembership(String membership) {
    this.membership = membership;
  }

  public String getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(String discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

}
package com.acolyptos.inventory.models;

import java.util.UUID;

public class Supplier {
  private String id, name, contactName, contactNumber, email;

  public Supplier(String id, String name, String contactName, String contactNumber, String email) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.contactName = contactName;
    this.contactNumber = contactNumber;
    this.email = email;
  }

  public String getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getContactName() {
    return contactName;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public String getEmail() {
    return email;
  }
}

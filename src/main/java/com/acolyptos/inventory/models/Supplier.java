package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Supplier {
  private ObjectId id;
  private String name, address, contactNumber, email;

  public Supplier(String name, String address, String contactNumber, String email) {
    this.name = name;
    this.address = address;
    this.contactNumber = contactNumber;
    this.email = email;
  }

  public ObjectId getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }

}

package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Person {
  protected ObjectId id;
  protected String name;
  protected String contactNumber;
  protected Role role;

  public Person(String name, String contactNumber, Role role) {
    this.name = name;
    this.contactNumber = contactNumber;
    this.role = role;
  }

  public ObjectId getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public Role getRole() {
    return role;
  }
}

package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Employee {
  private ObjectId id;
  private String name;
  private String email;
  private ObjectId roleID; // Store's the role ID

  public Employee(String name, String email, ObjectId roleID) {
    this.name = name;
    this.email = email;
    this.roleID = roleID;
  }

  public ObjectId getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public ObjectId getRoleID() {
    return roleID;
  }

  public void assistCustomer() {
    System.out.println(name + " is assisting a Customer.");
  }
}

package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class User {
  private ObjectId id;
  private String username;
  private String password;
  private String userType; // "EMPLOYEE" or "MANAGER"
  private ObjectId referenceID; // Stores EmployeeID or ManagerID

  public User(String username, String password, String userType, ObjectId referenceID) {
    this.username = username;
    this.password = password;
    this.userType = userType;
    this.referenceID = referenceID;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }

  public void setReferenceID(ObjectId referenceID) {
    this.referenceID = referenceID;
  }

  public ObjectId getID() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getUserType() {
    return userType;
  }

  public ObjectId getReferenceID() {
    return referenceID;
  }
}

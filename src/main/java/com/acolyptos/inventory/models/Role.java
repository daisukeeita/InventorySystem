package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Role {
  private ObjectId id;
  private String name;

  public Role(String name) {
    this.name = name;
  }

  public ObjectId getID() {
    return id;
  }

  public String getRoleName() {
    return name;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }
}

package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Role {
  private ObjectId id;
  private String name;

  public Role(ObjectId id, String name) {
    this.id = id;
    this.name = name;
  }

  public ObjectId getID() {
    return id;
  }

  public String getRoleName() {
    return name;
  }
}

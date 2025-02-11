package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Category {

  private ObjectId id;
  private String name;

  public Category(String name) {
    this.name = name;
  }

  public ObjectId getID() {
    return id;
  }

  public String getCategoryName() {
    return name;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }
}

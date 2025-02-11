package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class Product {

  private ObjectId id;
  private String name;
  private ObjectId categoryID;
  private ObjectId supplierID;
  private double price;
  private int stockQuantity;

  public Product(String name, ObjectId categoryID, ObjectId supplierID, double price, int stockQuantity) {
    this.name = name;
    this.categoryID = categoryID;
    this.supplierID = supplierID;
    this.price = price;
    this.stockQuantity = stockQuantity;
  }

  public ObjectId getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ObjectId getCategoryID() {
    return categoryID;
  }

  public ObjectId getSupplierID() {
    return supplierID;
  }

  public double getPrice() {
    return price;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }

  public void setPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative.");
    }

    this.price = price;
  }

  public void setStockQuantity(int stockQuantity) {
    if (stockQuantity < 0) {
      throw new IllegalArgumentException("Stock quantity cannot be negative.");
    }

    this.stockQuantity = stockQuantity;
  }
}

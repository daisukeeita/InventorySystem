package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

public class Sale {

  private ObjectId id;
  private ObjectId employeeID;
  private List<SaleItem> products;
  private double totalAmount;
  private Instant timestamp;

  public Sale(ObjectId employeeID, List<SaleItem> products, double totalAmount) {
    this.employeeID = employeeID;
    this.products = products;
    this.totalAmount = totalAmount;
    this.timestamp = Instant.now();
  }

  public ObjectId getID() {
    return id;
  }

  public ObjectId getEmployeeID() {
    return employeeID;
  }

  public List<SaleItem> getProducts() {
    return products;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setID(ObjectId id) {
    this.id = id;
  }
}

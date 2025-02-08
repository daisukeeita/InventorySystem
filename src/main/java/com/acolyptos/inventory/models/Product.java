package com.acolyptos.inventory.models;

public class Product {
  private int id, supplierID, stockQuantity;
  private String name, category;
  private double price;

  public Product(int id, String name, String category, int stockQuantity, double price, int supplierID) {
    this.id = id;
    this.name = name;
    this.category = category;
    setStockQuantity(stockQuantity);
    setPrice(price);
    this.supplierID = supplierID;
  }

  public int getID() {
    return id;
  }

  public int getSupplierID() {
    return supplierID;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  // Basic Validation for Product Price
  public void setPrice(double price) {
    if (price >= 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Price cannot be negative.");
    }
  }

  // Basic Validation for Stock Quantity
  public void setStockQuantity(int stockQuantity) {
    if (stockQuantity >= 0) {
      this.stockQuantity = stockQuantity;
    } else {
      throw new IllegalArgumentException("Stock Quantity cannot be negative.");
    }
  }

  // Basic Validation of Update Stock Quantity
  public void updateStock(int quantitySold) {
    if (quantitySold > 0 && stockQuantity >= quantitySold) {
      stockQuantity -= quantitySold;
    } else {
      throw new IllegalArgumentException("Invalid quantity or insufficient stock.");
    }
  }
}

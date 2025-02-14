package com.acolyptos.inventory.models;

import org.bson.types.ObjectId;

public class SaleItem {

  private ObjectId productID;
  private int quantity;
  private double priceSale;

  public SaleItem() {
  }

  public SaleItem(ObjectId productID, int quantity, double priceSale) {
    this.productID = productID;
    this.quantity = quantity;
    this.priceSale = priceSale;
  }

  public ObjectId getProductID() {
    return productID;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPriceSale() {
    return priceSale;
  }
}

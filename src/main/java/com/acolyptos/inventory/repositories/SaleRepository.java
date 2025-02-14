package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Sale;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class SaleRepository {

  private final MongoCollection<Document> saleCollection;

  public SaleRepository() {
    this.saleCollection = MongoDB.getDatabase().getCollection("sale");
  }

  // Insert Sale
  public void insertSale(Sale sale) {
    Document doc = new Document("employeeID", sale.getEmployeeID())
        .append("products", sale.getProducts())
        .append("totalAmount", sale.getTotalAmount())
        .append("timestamp", sale.getTimestamp());

    saleCollection.insertOne(doc);

    System.out.println("Sale recorded successfully.");
  }
}

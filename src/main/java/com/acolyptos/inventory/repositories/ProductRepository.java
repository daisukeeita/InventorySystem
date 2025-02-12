package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Product;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

  private final MongoCollection<Document> productCollection;

  public ProductRepository() {
    this.productCollection = MongoDB.getDatabase().getCollection("products");
  }

  // Insert Product
  public void insertProduct(Product product) {
    Document doc = new Document("name", product.getName())
        .append("categoryID", product.getCategoryID())
        .append("supplierID", product.getSupplierID())
        .append("price", product.getPrice())
        .append("stockQuantity", product.getStockQuantity());

    productCollection.insertOne(doc);

    System.out.println("Product inserted successfully: " + product.getName());
  }

  // Get all Products
  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();

    for (Document doc : productCollection.find()) {
      Product product = new Product(doc.getString("name"), doc.getObjectId("categoryID"), doc.getObjectId("supplierID"),
          doc.getDouble("price"), doc.getInteger("stockQuantity"));
      product.setID(doc.getObjectId("_id"));
    }

    return products;
  }

  // Get Products Based on category
  public List<Product> getProductsByCategory(ObjectId categoryId) {
    List<Product> products = new ArrayList<>();

    for (Document doc : productCollection.find(Filters.eq("categoryID", categoryId))) {
      Product product = new Product(doc.getString("name"), doc.getObjectId("categoryID"), doc.getObjectId("supplierID"),
          doc.getDouble("price"), doc.getInteger("stockQuantity"));
      product.setID(doc.getObjectId("_id"));
    }

    return products;
  }

  // Update a Product
  public void updateProduct(ObjectId id, String name, ObjectId categoryID, ObjectId supplierID, Double price,
      Integer stockQuantity) {
    List<Bson> values = new ArrayList<>();

    if (name != null && !name.isBlank()) {
      values.add(Updates.set("name", name));
    }
    if (categoryID != null) {
      values.add(Updates.set("categoryID", categoryID));
    }
    if (supplierID != null) {
      values.add(Updates.set("supplierID", supplierID));
    }
    if (price != null) {
      values.add(Updates.set("price", price));
    }
    if (stockQuantity != null) {
      values.add(Updates.set("stockQuantity", stockQuantity));
    }
  }
}

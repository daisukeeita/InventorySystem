package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Product;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import org.bson.Document;
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

  // Get Products Based on category
  public List<Product> getProductsByCategory(ObjectId id) {
    List<Product> products = new ArrayList<>();

    for (Document doc : productCollection.find(Filters.eq("categoryID", id))) {
      products.add(new Product(
          doc.getString("name"),
          doc.getObjectId("categoryID"),
          doc.getObjectId("supplierID"),
          doc.getDouble("price"),
          doc.getInteger("stockQuantity")));
    }

    return products;
  }
}

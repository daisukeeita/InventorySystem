package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Supplier;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.model.Filters;

public class SupplierRepository {

  private final MongoCollection<Document> supplierCollection;

  public SupplierRepository() {
    this.supplierCollection = MongoDB.getDatabase().getCollection("supplier");
  }

  // Insert Supplier
  public void insertSupplier(Supplier supplier) {
    Document doc = new Document("name", supplier.getName())
        .append("address", supplier.getAddress())
        .append("contactNumber", supplier.getContactNumber())
        .append("email", supplier.getEmail());

    supplierCollection.insertOne(doc);
    System.out.println("Supplier inserted: " + supplier.getName());
  }

  // Get All Suppliers
  public List<Supplier> getAllSuppliers() {
    List<Supplier> suppliers = new ArrayList<>();

    for (Document doc : supplierCollection.find()) {
      Supplier supplier = new Supplier(doc.getString("name"), doc.getString("address"), doc.getString("contactNumber"),
          doc.getString("email"));
      supplier.setID(doc.getObjectId("_id"));
      suppliers.add(supplier);

    }

    return suppliers;
  }

  // Retrieve a Supplier By Name
  public Supplier findSupplierByName(String name) {
    Document doc = supplierCollection.find(Filters.eq("name", name)).first();

    if (doc != null) {
      Supplier supplier = new Supplier(doc.getString("name"), doc.getString("address"), doc.getString("contactNumber"),
          doc.getString("email"));
      supplier.setID(doc.getObjectId("_id"));

      return supplier;
    }

    return null;
  }
}

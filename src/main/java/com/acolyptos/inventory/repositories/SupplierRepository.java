package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Supplier;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

  private final MongoCollection<Document> supplierCollection;

  public SupplierRepository() {
    this.supplierCollection = MongoDB.getDatabase().getCollection("supplier");
  }

  public void insertSupplier(Supplier supplier) {
    Document doc = new Document("name", supplier.getName())
        .append("address", supplier.getAddress())
        .append("contactNumber", supplier.getContactNumber())
        .append("email", supplier.getEmail());

    supplierCollection.insertOne(doc);
    System.out.println("Supplier inserted: " + supplier.getName());
  }

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
}

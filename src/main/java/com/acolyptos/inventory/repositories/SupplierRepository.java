package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Supplier;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

import org.bson.types.ObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;

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

  // Update a Supplier
  public void updateSupplier(ObjectId id, String name, String address, String contactNumber, String email) {
    List<Bson> values = new ArrayList<>();

    if (name != null && !name.isBlank()) {
      values.add(Updates.set("name", name));
    }
    if (address != null && !address.isBlank()) {
      values.add(Updates.set("address", address));
    }
    if (contactNumber != null && !contactNumber.isBlank()) {
      values.add(Updates.set("contactNumber", contactNumber));
    }
    if (email != null && !email.isBlank()) {
      values.add(Updates.set("email", email));
    }

    UpdateResult result = supplierCollection.updateOne(Filters.eq("_id", id), Updates.combine(values));

    if (result.getMatchedCount() > 0) {
      System.out.println("Supplier udpated successfully.");
    } else {
      System.out.println("Supplier not Found.");
    }
  }

  // Delete a Supplier
  public void deleteSupplier(ObjectId id) {
    DeleteResult result = supplierCollection.deleteOne(Filters.eq("_id", id));

    if (result.getDeletedCount() > 0) {
      System.out.println("Supplier deleted successfully.");
    } else {
      System.out.println("Supplier not Found.");
    }
  }

}

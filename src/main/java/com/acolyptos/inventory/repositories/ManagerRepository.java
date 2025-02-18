package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {

  private final MongoCollection<Document> managerCollection;

  public ManagerRepository() {
    this.managerCollection = MongoDB.getDatabase().getCollection("managers");
  }

  // Insert Manager
  public InsertOneResult insertManager(Manager manager) {
    Document doc = new Document("name", manager.getName())
        .append("email", manager.getEmail())
        .append("roleID", manager.getRoleID());

    return managerCollection.insertOne(doc);
  }

  // Get All Managers
  public List<Manager> getAllManagers() {
    List<Manager> managers = new ArrayList<>();

    for (Document doc : managerCollection.find()) {
      Manager manager = new Manager(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      manager.setID(doc.getObjectId("_id"));

      managers.add(manager);
    }

    return managers;
  }

  // Find Manager by ID
  public Manager findManagerByID(ObjectId id) {
    Document doc = managerCollection.find(Filters.eq("_id", id)).first();

    if (doc != null) {
      Manager manager = new Manager(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      manager.setID(doc.getObjectId("_id"));

      return manager;
    }

    return null;
  }

  // Find Manager by Name
  public Manager findManagerByName(String name) {
    Document doc = managerCollection.find(Filters.eq("name", name)).first();

    if (doc != null) {
      Manager manager = new Manager(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      manager.setID(doc.getObjectId("_id"));

      return manager;
    }

    return null;
  }

  // Update a Manager
  public void updateManager(ObjectId id, String name, String email, ObjectId roleID) {
    List<Bson> values = new ArrayList<>();

    if (name != null && !name.isBlank()) {
      values.add(Updates.set("name", name));
    }
    if (email != null && !email.isBlank()) {
      values.add(Updates.set("email", email));
    }
    if (roleID != null) {
      values.add(Updates.set("roleID", roleID));
    }

    UpdateResult result = managerCollection.updateOne(Filters.eq("_id", id), Updates.combine(values));

    if (result.getMatchedCount() > 0) {
      System.out.println("Manager updated successfully.");
    } else {
      System.out.println("Manager not Found.");
    }
  }

  // Delete a Manager
  public void deleteManager(ObjectId id) {
    DeleteResult result = managerCollection.deleteOne(Filters.eq("_id", id));

    if (result.getDeletedCount() > 0) {
      System.out.println("Manager deleted successfully.");
    } else {
      System.out.println("Manager not Found.");
    }
  }

}

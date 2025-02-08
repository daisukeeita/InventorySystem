package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Manager;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {

  private final MongoCollection<Document> managerCollection;

  public ManagerRepository() {
    this.managerCollection = MongoDB.getDatabase().getCollection("managers");
  }

  public void insertManager(Manager manager) {
    Document doc = new Document("name", manager.getName())
        .append("email", manager.getEmail())
        .append("roleID", manager.getRoleID());

    managerCollection.insertOne(doc);

    System.out.println("Manager inserted successfully.");
  }

  public List<Manager> getAllManagers() {
    List<Manager> managers = new ArrayList<>();

    for (Document doc : managerCollection.find()) {
      managers.add(new Manager(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID")));
    }

    return managers;
  }

}

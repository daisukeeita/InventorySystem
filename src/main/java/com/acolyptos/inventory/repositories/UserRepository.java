package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.models.User;
import com.acolyptos.inventory.database.MongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;

public class UserRepository {
  private final MongoCollection<Document> userCollection;

  public UserRepository() {
    this.userCollection = MongoDB.getDatabase().getCollection("users");
  }

  public InsertOneResult insertUser(User user) {
    Document doc = new Document("username", user.getUsername())
        .append("password", user.getPassword())
        .append("userType", user.getUserType())
        .append("referenceID", user.getReferenceID());

    System.out.println("User registered successfully.");

    return userCollection.insertOne(doc);
  }

  public User findUserbyUsername(String username) {
    Document doc = userCollection.find(Filters.eq("username", username)).first();

    if (doc != null) {
      User user = new User(doc.getString("username"), doc.getString("password"), doc.getString("userType"),
          doc.getObjectId("referenceID"));
      user.setID(doc.getObjectId("_id"));

      return user;
    }

    return null;
  }
}

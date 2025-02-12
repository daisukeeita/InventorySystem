package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Role;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.types.ObjectId;

public class RoleRepository {

  private final MongoCollection<Document> roleCollection;

  public RoleRepository() {
    this.roleCollection = MongoDB.getDatabase().getCollection("roles");
  }

  // Insert Role
  public void insertRole(Role role) {
    Document doc = new Document("roleName", role.getRoleName());
    roleCollection.insertOne(doc);

    System.out.println("Role inserted: " + role.getRoleName());
  }

  // Find a Role by Name
  public Role findRoleByName(String roleName) {
    Document doc = roleCollection.find(Filters.eq("roleName", roleName)).first();

    if (doc != null) {
      final String name = doc.getString("roleName");

      Role role = new Role(name);
      return role;
    }

    return null;
  }

  // Delete a Role
  public void deleteRole(ObjectId id) {
    DeleteResult result = roleCollection.deleteOne(Filters.eq("_id", id));

    if (result.getDeletedCount() > 0) {
      System.out.println("Role deleted successfully.");
    } else {
      System.out.println("Role not Found.");
    }
  }
}

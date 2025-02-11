package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Category;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

  private final MongoCollection<Document> categoryCollection;

  public CategoryRepository() {
    this.categoryCollection = MongoDB.getDatabase().getCollection("categories");
  }

  // Insert Category
  public void insertCategory(Category category) {
    Document doc = new Document("name", category.getCategoryName());

    categoryCollection.insertOne(doc);

    System.out.println("Category inserted successfully: " + category.getCategoryName());
  }

  // Get All Categories
  public List<Category> getAllCategories() {
    List<Category> categories = new ArrayList<>();

    for (Document doc : categoryCollection.find()) {
      Category category = new Category(doc.getString("name"));
      category.setID(doc.getObjectId("_id"));

      categories.add(category);
    }

    return categories;
  }

  // Get Category by Id
  public Category findCategoryByID(ObjectId id) {
    Document doc = categoryCollection.find(Filters.eq("_id", id)).first();

    if (doc != null) {
      Category category = new Category(doc.getString("name"));
      category.setID(doc.getObjectId("_id"));

      return category;
    }

    return null;
  }

  // Get Category by Name
  public Category findCategoryByName(String name) {
    Document doc = categoryCollection.find(Filters.eq("name", name)).first();

    if (doc != null) {
      Category category = new Category(doc.getString("name"));
      category.setID(doc.getObjectId("_id"));

      return category;
    }

    return null;
  }

}

package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Category;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

  private final MongoCollection<Document> categoryCollection;

  public CategoryRepository() {
    this.categoryCollection = MongoDB.getDatabase().getCollection("categories");
  }

  public void insertCategory(Category category) {
    Document doc = new Document("name", category.getCategoryName());

    categoryCollection.insertOne(doc);

    System.out.println("Category inserted successfully: " + category.getCategoryName());
  }

  public List<Category> getAllCategories() {
    List<Category> categories = new ArrayList<>();

    for (Document doc : categoryCollection.find()) {
      Category category = new Category(doc.getString("name"));
      category.setID(doc.getObjectId("_id"));

      categories.add(category);
    }

    return categories;
  }
}

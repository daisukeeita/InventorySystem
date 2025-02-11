
package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Category;
import com.acolyptos.inventory.repositories.CategoryRepository;

import org.bson.types.ObjectId;
import java.util.List;

public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService() {
    this.categoryRepository = new CategoryRepository();
  }

  // Create Category
  public void createCategory(String name) {
    Category category = new Category(name);

    categoryRepository.insertCategory(category);
  }

  // List all Categories
  public List<Category> listCategories() {
    return categoryRepository.getAllCategories();
  }

  // Retrieve a category by ID
  public Category getCategoryByID(ObjectId id) {
    return categoryRepository.findCategoryByID(id);
  }

  // Retrieve a category by Name
  public Category getCategoryByName(String name) {
    return categoryRepository.findCategoryByName(name);
  }
}

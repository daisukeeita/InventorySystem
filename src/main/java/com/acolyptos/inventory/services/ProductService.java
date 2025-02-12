package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Product;
import com.acolyptos.inventory.models.Category;
import com.acolyptos.inventory.models.Supplier;
import com.acolyptos.inventory.repositories.ProductRepository;
import com.acolyptos.inventory.repositories.CategoryRepository;
import com.acolyptos.inventory.repositories.SupplierRepository;

import java.util.List;

import org.bson.types.ObjectId;

public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final SupplierRepository supplierRepository;

  public ProductService() {
    this.productRepository = new ProductRepository();
    this.categoryRepository = new CategoryRepository();
    this.supplierRepository = new SupplierRepository();
  }

  // Create Product
  public void createProduct(String name, String categoryName, String supplierName, double price, int stockQuantity) {
    Category category = categoryRepository.findCategoryByName(categoryName);
    Supplier supplier = supplierRepository.findSupplierByName(supplierName);

    if (category == null) {
      System.out.println("Category not Found: " + categoryName);
    }

    if (supplier == null) {
      System.out.println("Supplier not Found: " + supplierName);
    }

    Product product = new Product(name, category.getID(), supplier.getID(), price, stockQuantity);
    productRepository.insertProduct(product);
  }

  // List All Products
  public List<Product> listAllProducts() {
    return productRepository.getAllProducts();
  }

  // List Products by Category
  public List<Product> listProductsByCategory(String categoryName) {
    Category category = categoryRepository.findCategoryByName(categoryName);

    if (category == null) {
      System.out.println("Category not Found: " + categoryName);
    }

    return productRepository.getProductsByCategory(category.getID());
  }

  // Update a Product
  public void updateProduct(ObjectId id, String name, ObjectId categoryID, ObjectId supplierID, double price,
      int stockQuantity) {
    productRepository.updateProduct(id, name, categoryID, supplierID, price, stockQuantity);
  }

}

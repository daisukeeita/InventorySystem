package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Product;
import com.acolyptos.inventory.models.Category;
import com.acolyptos.inventory.models.Supplier;
import com.acolyptos.inventory.repositories.ProductRepository;
import com.acolyptos.inventory.repositories.CategoryRepository;
import com.acolyptos.inventory.repositories.SupplierRepository;

import java.util.List;

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

    Product product = new Product(name, category.getID(), supplier.getID(), price, stockQuantity);
    productRepository.insertProduct(product);
  }

  // List Products by Category
  public List<Product> listProductsByCategory(String categoryName) {
    Category category = categoryRepository.findCategoryByName(categoryName);

    if (category == null) {
      System.out.println("Category not Found: " + categoryName);
    }

    return productRepository.getProductsByCategory(category.getID());
  }

}

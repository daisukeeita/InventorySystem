package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Sale;
import com.acolyptos.inventory.models.SaleItem;
import com.acolyptos.inventory.models.Product;
import com.acolyptos.inventory.repositories.ProductRepository;
import com.acolyptos.inventory.repositories.SaleRepository;

import org.bson.types.ObjectId;

import java.util.List;

public class SaleService {

  private final SaleRepository saleRepository;
  private final ProductRepository productRepository;
  private final ProductService productService;

  public SaleService() {
    this.saleRepository = new SaleRepository();
    this.productRepository = new ProductRepository();
    this.productService = new ProductService();
  }

  // Register Sales
  public void registerSale(ObjectId employeeID, List<SaleItem> items) {
    double totalAmount = 0.0;

    for (SaleItem item : items) {
      Product product = productService.findProductByID(item.getProductID());

      if (product == null) {
        System.out.println("Product not found: " + item.getProductID());
        return;
      }

      int currentStock = product.getStockQuantity();
      if (currentStock < item.getQuantity()) {
        System.out.println("Not enough stock for product: " + product.getName());
        return;
      }

      totalAmount += item.getQuantity() * item.getPriceSale();

      productRepository.reduceProductStock(item.getProductID(), item.getQuantity());
    }

    Sale sale = new Sale(employeeID, items, totalAmount);
    saleRepository.insertSale(sale);

    System.out.println("Sale recorded successfully.");
  }
}

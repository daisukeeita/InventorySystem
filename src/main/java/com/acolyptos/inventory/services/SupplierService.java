package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Supplier;
import com.acolyptos.inventory.repositories.SupplierRepository;

import java.util.List;

import org.bson.types.ObjectId;

public class SupplierService {

  private final SupplierRepository supplierRepository;

  public SupplierService() {
    this.supplierRepository = new SupplierRepository();
  }

  // Create Supplier
  public void createSupplier(String name, String address, String contactNumber, String email) {
    Supplier supplier = new Supplier(name, address, contactNumber, email);

    supplierRepository.insertSupplier(supplier);
  }

  // List All Supplier
  public List<Supplier> listSuppliers() {
    return supplierRepository.getAllSuppliers();
  }

  // Get Supplier by Name
  public Supplier findSupplierByName(String name) {
    return supplierRepository.findSupplierByName(name);
  }

  // Update a Supplier
  public void updateSupplier(ObjectId id, String name, String address, String contactNumber, String email) {
    supplierRepository.updateSupplier(id, name, address, contactNumber, email);
  }

  // Delete a Supplier
  public void deleteSupplier(ObjectId id) {
    supplierRepository.deleteSupplier(id);
  }
}

package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Supplier;
import com.acolyptos.inventory.repositories.SupplierRepository;

import java.util.List;

public class SupplierService {

  private final SupplierRepository supplierRepository;

  public SupplierService() {
    this.supplierRepository = new SupplierRepository();
  }

  public void createSupplier(String name, String address, String contactNumber, String email) {
    Supplier supplier = new Supplier(name, address, contactNumber, email);

    supplierRepository.insertSupplier(supplier);
  }

  public List<Supplier> listSuppliers() {
    return supplierRepository.getAllSuppliers();
  }
}

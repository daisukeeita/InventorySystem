package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Manager;
import com.acolyptos.inventory.models.Role;

import com.acolyptos.inventory.repositories.ManagerRepository;
import com.acolyptos.inventory.repositories.RoleRepository;

import java.util.List;

import org.bson.types.ObjectId;

public class ManagerService {

  private final ManagerRepository managerRepository;
  private final RoleRepository roleRepository;

  public ManagerService() {
    this.managerRepository = new ManagerRepository();
    this.roleRepository = new RoleRepository();
  }

  // Create a Manager
  public void createManager(String name, String email, String roleName) {
    Role role = roleRepository.findRoleByName(roleName);

    if (role == null) {
      System.out.println("Role not Found: " + roleName);
      return;
    }

    Manager manager = new Manager(name, email, role.getID());
    managerRepository.insertManager(manager);
  }

  // Get all Managers
  public List<Manager> listManagers() {
    return managerRepository.getAllManagers();
  }

  // Get a Manager by ID
  public Manager getManagerById(ObjectId id) {
    return managerRepository.findManagerByID(id);
  }

  // Get a Manager by Name
  public Manager getManagerByName(String name) {
    return managerRepository.findManagerByName(name);
  }

  // Update Manager
  public void updateManager(ObjectId id, String name, String email, ObjectId roleID) {
    managerRepository.updateManager(id, name, email, roleID);
  }

  // Delete Manager
  public void deleteManager(ObjectId id) {
    managerRepository.deleteManager(id);
  }
}

package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Manager;
import com.acolyptos.inventory.models.Role;

import com.acolyptos.inventory.repositories.ManagerRepository;
import com.acolyptos.inventory.repositories.RoleRepository;

import java.util.List;

public class ManagerService {

  private final ManagerRepository managerRepository;
  private final RoleRepository roleRepository;

  public ManagerService() {
    this.managerRepository = new ManagerRepository();
    this.roleRepository = new RoleRepository();
  }

  public void createManager(String name, String email, String roleName) {
    Role role = roleRepository.findRoleByName(roleName);

    if (role == null) {
      System.out.println("Role not Found: " + roleName);
      return;
    }

    Manager manager = new Manager(name, email, role.getID());
    managerRepository.insertManager(manager);
  }

  public List<Manager> listManagers() {
    return managerRepository.getAllManagers();
  }
}

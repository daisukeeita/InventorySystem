package com.acolyptos.inventory;

import com.acolyptos.inventory.services.EmployeeService;
import com.acolyptos.inventory.services.ManagerService;

public class Main {

  public static void main(String[] args) {
    System.out.println("Inventory System Initialized.");

    EmployeeService employeeService = new EmployeeService();
    ManagerService managerService = new ManagerService();

    employeeService.createEmployee("Employee3", "lenalee@example.com", "Employee");
    employeeService.listEmployees()
        .forEach(emp -> System.out.println(emp.getName() + " - " + emp.getEmail() + " - Role ID: " + emp.getRoleID()));

    managerService.createManager("Manager2", "kanda@example.com", "Manager");
    managerService.listManagers()
        .forEach(emp -> System.out.println(emp.getName() + " - " + emp.getEmail() + " - Role ID: " + emp.getRoleID()));
  }
}

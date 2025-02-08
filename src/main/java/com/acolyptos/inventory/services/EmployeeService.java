package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.Employee;
import com.acolyptos.inventory.models.Role;
import com.acolyptos.inventory.repositories.EmployeeRepository;
import com.acolyptos.inventory.repositories.RoleRepository;

import java.util.List;

public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final RoleRepository roleRepository;

  public EmployeeService() {
    this.employeeRepository = new EmployeeRepository();
    this.roleRepository = new RoleRepository();
  }

  // Create Employee
  public void createEmployee(String name, String email, String roleName) {
    Role role = roleRepository.findRoleByName(roleName);

    if (role == null) {
      System.out.println("Role not Found: " + roleName);
      return;
    }

    Employee employee = new Employee(name, email, role.getID());
    employeeRepository.insertEmployee(employee);
  }

  // Get All Employees
  public List<Employee> listEmployees() {
    return employeeRepository.getAllEmployees();
  }
}

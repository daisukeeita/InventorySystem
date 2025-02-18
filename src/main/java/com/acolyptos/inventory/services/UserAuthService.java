package com.acolyptos.inventory.services;

import com.acolyptos.inventory.models.User;
import com.acolyptos.inventory.models.Role;
import com.acolyptos.inventory.models.Employee;
import com.acolyptos.inventory.models.Manager;
import com.acolyptos.inventory.utilities.PasswordUtility;
import com.acolyptos.inventory.repositories.UserRepository;
import com.acolyptos.inventory.repositories.RoleRepository;
import com.acolyptos.inventory.repositories.EmployeeRepository;
import com.acolyptos.inventory.repositories.ManagerRepository;

import org.bson.types.ObjectId;

public class UserAuthService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final EmployeeRepository employeeRepository;
  private final ManagerRepository managerRepository;

  public UserAuthService() {
    this.userRepository = new UserRepository();
    this.roleRepository = new RoleRepository();
    this.employeeRepository = new EmployeeRepository();
    this.managerRepository = new ManagerRepository();
  }

  private Object createUserEntity(String name, String email, Role role) {
    if (role.getRoleName().equals("manager")) {
      Manager manager = new Manager(name, email, role.getID());
      return managerRepository.insertManager(manager);
    } else if (role.getRoleName().equals("employee")) {
      Employee employee = new Employee(name, email, role.getID());
      return employeeRepository.insertEmployee(employee);
    }

    return null;
  }

  public void registerUser(String name, String email, String roleName, String username, String password) {
    Role role = roleRepository.findRoleByName(roleName);
    String hashedPassword = PasswordUtility.hashPassword(password);

    if (userRepository.findUserbyUsername(username) != null) {
      System.out.println("Username already taken! Please use another one.");
      return;
    }

    if (role == null) {
      System.out.println("Role not found: " + roleName);
      return;
    }

    // Check if Role is an Employee or a Manager
    Object referenceEntity = createUserEntity(name, email, role);

    ObjectId referenceID = (referenceEntity instanceof Manager) ? ((Manager) referenceEntity).getID()
        : ((Employee) referenceEntity).getID();

    User user = new User(username, hashedPassword, role.getRoleName(), referenceID);
    userRepository.insertUser(user);
  }

  public User loginUser(String username, String password) {
    User user = userRepository.findUserbyUsername(username);

    if (user == null) {
      System.out.println("User not found! Please try again.");
      return null;
    }

    if (PasswordUtility.checkPassword(password, user.getPassword())) {
      System.out.println("User login successfull: " + user.getUserType());
      return user;
    } else {
      System.out.println("Incorrect Password!");
      return null;
    }
  }
}

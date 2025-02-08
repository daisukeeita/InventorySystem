package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Employee;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

  private final MongoCollection<Document> employeeCollection;

  public EmployeeRepository() {
    this.employeeCollection = MongoDB.getDatabase().getCollection("employees");
  }

  // Insert Employee
  public void insertEmployee(Employee employee) {
    Document doc = new Document("name", employee.getName())
        .append("email", employee.getEmail())
        .append("roleID", employee.getRoleID());

    employeeCollection.insertOne(doc);

    System.out.println("Employee inserted succesfully.");
  }

  // Get All Employees
  public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    for (Document doc : employeeCollection.find()) {
      employees.add(new Employee(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID")));
    }

    return employees;
  }

  // Find Employee by ID
  // TODO: Research for the correct syntax for finding an Employee ID
  public Employee findEmployeeByID(ObjectId id) {
    Document doc = employeeCollection.find(Filters.eq("_id", id)).first();

    if (doc != null) {
      return new Employee(doc.getString("name"), doc.getString("email"),
          doc.getObjectId("roleID"));
    }

    return null;
  }
}

package com.acolyptos.inventory.repositories;

import com.acolyptos.inventory.database.MongoDB;
import com.acolyptos.inventory.models.Employee;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.conversions.Bson;
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
      Employee employee = new Employee(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      employee.setID(doc.getObjectId("_id"));

      employees.add(employee);
    }

    return employees;
  }

  // Find Employee by ID
  public Employee findEmployeeByID(ObjectId id) {
    Document doc = employeeCollection.find(Filters.eq("_id", id)).first();

    if (doc != null) {
      Employee employee = new Employee(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      employee.setID(doc.getObjectId("_id"));

      return employee;
    }

    return null;
  }

  // Find Employee by Name
  public Employee findEmployeeByName(String name) {
    Document doc = employeeCollection.find(Filters.eq("name", name)).first();

    if (doc != null) {
      Employee employee = new Employee(doc.getString("name"), doc.getString("email"), doc.getObjectId("roleID"));
      employee.setID(doc.getObjectId("_id"));

      return employee;
    }

    return null;
  }

  // Update an Employee
  public void updateEmployee(ObjectId id, String name, String email, ObjectId roleID) {
    List<Bson> values = new ArrayList<>();

    if (name != null && !name.isBlank()) {
      values.add(Updates.set("name", name));
    }
    if (email != null && !email.isBlank()) {
      values.add(Updates.set("email", name));
    }
    if (roleID != null) {
      values.add(Updates.set("roleID", roleID));
    }

    UpdateResult result = employeeCollection.updateOne(Filters.eq("_id", id), Updates.combine(values));

    if (result.getMatchedCount() > 0) {
      System.out.println("Employee updated succesfully.");
    } else {
      System.out.println("Employee not Found.");
    }
  }

  // Delete an Employee
  public void deleteEmployee(ObjectId id) {
    DeleteResult delete = employeeCollection.deleteOne(Filters.eq("_id", id));

    if (delete.getDeletedCount() > 0) {
      System.out.println("Employee deleted succesfully.");
    } else {
      System.out.println("Employee not found.");
    }
  }
}

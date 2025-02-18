# Inventory Management System

## Project Overview

This is a simple inventory management system designed for a mini-mart setup with product categories similar to those found in wet markets. The system is built using Java and MongoDB(Atlas)and supports basic CRUD operations for managing employees, products, suppliers, sales transactions, and stock management.

## Features

- **Employee and Manager Management**: CRUD operations for managing employees and managers, with role-based control.
- **Product and Category Management**: Predefined product categories with the ability to add, update, and remove items.
- **Sales Transactions**: Register sales, update stock levels accordingly
- **Supplier Management**: Manage supplier details
- **MongoDB Integration**: Uses MongoDB Atlas for storing and retrieving data.

## Tech Stacks

- **Backend**: Java
- **Database**: MongoDB Atlas
- **ORM**: MongoDB Java Driver
- **Environment Variables**: dotenv

## Folder Structure

```
InventorySystem
|-src
  |-main
    |-java
      |-com
        |-acolyptos
          |-inventory
            |-controllers
            |-database
            |-models
            |-repositories
            |-services
            Main.java
|-README.md
|-.gitignore
```

## Current Database Collections

- **roles**: Defines roles like `Manager` and `Employee`.
- **employees**: Stores employee details with assigned role IDs.
- **managers**: Stores manager details with assigned role IDs.

## TODO List

### Back-end (MongoDB + Java)

- [x] CRUD Operations for Employees, Managers, and Roles
- [x] CRUD Operations for Suppliers, Categories, and Products
- [x] CRUD Operations for Sales and Sales Items
- [x] Sales Transactions and stock updates logic

- [x] Implement User Authentication System (Login System)

  - [ ] Check if User Registration and Login is working and stable

- [ ] Error Handling and Validation

### Front-end (To be decided)

- [ ] Decide on Front-end Framework

  - [ ] React (Typescript/JavaScript)
  - [ ] JavaFX (Java-based UI)

- [ ] Basic UI Components

  - [ ] Login Page
  - [ ] Dashboard (view Inventory, Sales, etc.)
  - [ ] Product Management (add/update/delete products)
  - [ ] Sales Entry Form (record customer purchases)

- [ ] Connect Front-end with Back-end

  - [ ] Use API calls (e.g. REST API) to interact with the back-end

- [x] Insert and retrieve Employees and Managers
- [x] Assign roles to Employees and Managers
- [ ] Fetch Role Names instead of just IDs
- [x] Implement Update and Delete for Employees
- [x] Implement Inventory Stock Updates

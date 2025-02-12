# Inventory Management System

## Project Overview

This is a simple Inventory Management System designed for _mini-mart_ that sells items similar to a _wet market_. The system includes **manual entry** for stock, **categorization of products**, and **employee management**.
It is built using **Java** and **MongoDB(Atlas)**.

## Features

- **Product Management**: Add, update, and retrieve product details.
- **Role-Based Employee System**: Supports Manager and Employees with predefined roles.
- **MongoDB Atlas Integration**: Stores inventory and employee data.

## Tech Stacks

- **Backend**: Java
- **Database**: MongoDB Atlas
- **ORM**: MongoDB Java Driver

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

- [x] Insert and retrieve Employees and Managers
- [x] Assign roles to Employees and Managers
- [ ] Fetch Role Names instead of just IDs
- [x] Implement Update and Delete for Employees
- [ ] Implement Inventory Stock Updates

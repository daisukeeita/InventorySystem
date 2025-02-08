#!/bin/bash

# Clean previous build files
mvn clean

# Compile the project
mvn compile

# Run the project quietly (only shows println and errors)
mvn -q exec:java -Dexec.mainClass="com.acolyptos.inventory.Main"

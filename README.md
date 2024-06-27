# Management service

## Overview

This project is a Task manager service built using Scala and Akka HTTP. It provides APIs for managing tasks and users.

## Project Structure

- Main.scala: Entry point for the application
- Tables.scala: Defines the database schema using Slick
- DatabaseService.scala: Contains methods for interacting with the database
- TaskRoutes.scala: Defines the HTTP routes for tasks and users
- application.conf: Configuration file for the application
- logback.xml: Configuration file for logging
- flyway: Contains database migration scripts

# Prerequisites
## Ensure you have the following installed:
- Scala
- SBT (Scala Build Tool)
- PostgreSQL
- Flyway (for database migrations)

## Features

- **Task Manager**: Create, update, delete tasks.
- **User Manager**: Manage users and their tasks.
- **RESTful APIs**: Expose RESTful APIs for easy integration with client applications.

## Requirements

- Java 8 or higher
- Scala 3.3.3
- SBT 1.10.0
- Docker(optional for running PostgreSQL)
- PostgreSQL (for database)


## Setup Instructions

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your_username/management-service.git
   cd management-service
   ```

2. **Set up the database**:

    - Create a PostgreSQL database manually. You can use the following command:

      ```bash
      sudo -u postgres psql -c "CREATE DATABASE management_service;"
      ```


3. **Compile and run the application**:

   ```bash
   sbt run
   ```

4. **Access the API**:

   You can access the API at `http://localhost:8080`.
5. ## API Endpoints:
   ## Tasks:

- POST /tasks: Create a new task.
- GET /tasks/{id}: Retrieve a task by ID.
- PUT /tasks/{id}: Update a task by ID.
- DELETE /tasks/{id}: Delete a task by ID.
    ## Users:

- POST /users: Create a new user.
- GET /users/{id}: Retrieve a user by ID.
- PUT /users/{id}: Update a user by ID.
- DELETE /users/{id}: Delete a user by ID.

## Configuration

- Database configuration: Update `src/main/resources/application.conf` with your database settings.

## Dependencies

- Akka HTTP: Web server framework.
- Slick: JDBC wrapper for database access.
- Scala Logging: Logging library.
- Flyway: Database migration tool.
- PureConfig: Configuration library.


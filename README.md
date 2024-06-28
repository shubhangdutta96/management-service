# Management service
 This is simple task management service built using scala, Akka HTTP, Slick, and PostgreSQL,. It provides basic CRUD operations for task and users

# Prerequisites
## Ensure you have the following installed:
- Scala
- SBT (Scala Build Tool)
- PostgreSQL
- Flyway (for database migrations)

## Project Structure
- Main Application: `Main.scala`
- Routes: `TaskRoutes.scala`
- Database Service: `DatabaseService.scala`
- Tables: `Tables.scala`
- Test: `TaskRoutesSpec.scala`
- Configurations: `application.conf`, `logback.xml`
- Database Migrations: `flyway/migrations`

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

    - Install PostgreSQL and create new database:

      ```bash
      sudo -i -u postgres
      psql
      CREATE DATABASE postgres;
      CREATE USER postgres WITH ENCRYPTED PASSWORD 'Shubhang@09';
      GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;
      ```
   - Save the following SQL script as schema.sql:
       ```Sql
      CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL
      );

      CREATE TABLE tasks (
          id SERIAL PRIMARY KEY,
          title VARCHAR(255) NOT NULL,
          description TEXT NOT NULL,
          user_id INTEGER NOT NULL REFERENCES users(id)
      );
      ```
      ```bash
      psql -h localhost -U postgres -d postgres -f schema.sql
      ```


3. **Compile and run the application**:

   ```bash
   sbt run
   ```

4. **Access the API**:

   You can access the API at `http://localhost:8080`.

5. ## Testing the applications
- Run the tests:
  ```bash
   sbt test
   ```

   
6. ## API Endpoints:
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

- The configuration file `application.conf` contains settings for the database connection and server.
- Database configuration: Update `src/main/resources/application.conf` with your database settings.

## Dependencies

- Akka HTTP: Web server framework.
- Slick: JDBC wrapper for database access.
- Scala Logging: Logging library.
- Flyway: Database migration tool.
- PureConfig: Configuration library.


# Worktime Server

This repository contains the server side of the Worktime project.

## Running the server

### Option 1: Manual Setup

To run the server manually, WildFly 35.0.1 Final is required.
You can download it from the [official website](https://www.wildfly.org/downloads/).

### Option 2: Using Docker Compose

This project includes a Docker Compose configuration that sets up both WildFly 35.0.1 and MongoDB without password protection.

#### Prerequisites

- Docker and Docker Compose installed on your system
- Maven for building the application

#### Steps to run with Docker Compose

1. Build the application:
   ```bash
   mvn clean package
   ```

2. Start the Docker Compose environment:
   ```bash
   docker-compose up -d
   ```

3. Access the application:
   - Web application: http://localhost:8080/worktime-server
   - WildFly admin console: http://localhost:9990

4. To stop the environment:
   ```bash
   docker-compose down
   ```

#### Docker Compose Configuration

The Docker Compose setup includes:

- WildFly 35.0.1 Final for running the Java EE application
- MongoDB (latest version) without password protection
- Shared network namespace between WildFly and MongoDB to allow the application to connect to MongoDB using localhost
- Volume mapping for MongoDB data persistence

The application WAR file is automatically deployed to WildFly when the container starts.

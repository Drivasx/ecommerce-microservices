# Spring Commerce: Building a Resilient E-commerce System with Spring Boot Microservices

This repository contains code for a microservices-based e-commerce application developed using Spring Boot. This project demonstrates how to build, test, and deploy microservices using Spring Cloud and Docker.

## Architecture Overview

The application is composed of multiple microservices:

- **Product Service**: Manages product information (MongoDB)
- **Order Service**: Handles customer orders (PostgreSQL)
- **Inventory Service**: Tracks product inventory (PostgreSQL)


## Tech Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data MongoDB** (Product Service)
- **Spring Data JPA** (Order & Inventory Services)
- **PostgreSQL** (Order & Inventory Services)
- **MongoDB** (Product Service)
- **Lombok** (Reduce boilerplate code)
- **Docker & Docker Compose** (Containerization)
- **TestContainers** (Integration testing)
- **API Gateway** (Unique access point)
- **Netflix Eureka** (Microservices Registration)
- **Feign Client** (Synchronous communication)
- **Kafka** (Asynchronous communication)
- **Keycloack** (Authorization and Authentication)


### NEXT STEPS...


### Start the Databases

```bash
docker-compose up -d
```

This will start:
- MongoDB (for Product Service)
- PostgreSQL (for Order Service and Inventory Service)

### Building the Services

Build all services using Maven:

```bash
mvn clean package
```

Or we can build each service individually:

```bash
cd product-service
mvn clean package
cd ../order-service
mvn clean package
cd ../inventory-service
mvn clean package
```

### Running the Services

Each service runs on a different port:

- Product Service: 8081
- Order Service: 8082
- Inventory Service: 8083

You we can run each service:

```bash
cd product-service
mvn spring-boot:run

cd order-service
mvn spring-boot:run

cd inventory-service
mvn spring-boot:run
```

## Database Setup

### MongoDB (Product Service)

The docker-compose file automatically sets up MongoDB with the following configuration:
- Port: 27017
- Username: root
- Password: password
- Database: product_db

### PostgreSQL (Order Service & Inventory Service)

The docker-compose file sets up PostgreSQL with:
- Port: 3306
- Username: services_user
- Password: services_user
- Databases: 
  - orders_db
  - inventory_db


## API Documentation

### Product Service

- **Create Product**
  - Endpoint: POST /api/v1/products
  - Request Body:
    ```json
    {
      "_id": "66f3a2b1d2f94f0a9c876a21",
      "name": "Camiseta Oversize",
      "description": "Camiseta negra estilo streetwear con estampado gráfico",
      "category": "Ropa",
      "price": 89.99,
      "stock": 120,
      "sku": "TSH-001-BLK",
      "active": true
    }
    ```
  - Response: 201 CREATED

- **Get All Products**
  - Endpoint: GET /api/v1/products
  - Response: List of products

### Order Service

- **Place Order**
  - Endpoint: POST /api/v1/orders
  - Request Body:
    ```json
    {
      "customerId": "USR-001",
      "items": [
          {
              "sku": "TSH-001-BLK",
              "quantity": 2
          }
      ]
    }
    ```
  - Response: 201 CREATED

### NEXT STEPS...

### Inventory Service

- **Check Inventory**
  - Endpoint: GET /api/inventory?skuCode=iphone_15&quantity=1
  - Response: Boolean (true if in stock, false otherwise)

## Testing

The project uses JUnit and TestContainers for integration testing. Run tests with:

```bash
mvn test
```

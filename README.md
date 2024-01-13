# BCNC Project for Inditex

This README.md provides detailed information about the BCNC project developed for the Inditex company. The project utilizes Spring Boot as a development framework, H2 as a database, and follows a Test-Driven Development (TDD) architecture.

## Project Description

BCNC is a project designed for Inditex, focusing on inventory management and stock control in the chain's stores. The application allows for operations related to product management, inventory tracking, and report generation to facilitate decision-making.

## Technologies Used
- **Spring Boot:** Java application development framework that simplifies the creation of Spring-based applications with minimal configuration.
- **H2 Database:** In-memory database providing efficient storage for development and testing purposes.
- **TDD (Test-Driven Development):** Development methodology that emphasizes writing tests before writing production code, ensuring software quality and integrity.

## Why used that technologies
* In this project I have used the TDD architecture since there were some main tests, and I decided that it was better to set up the structure of the tests and then set up the entire project service and that is why we have also used an in-memory database.
* We have used lombok to avoid filling the classes with getters and setter functions, thus having a cleaner code.
* We also have the mapstruct dependency to map the objects from DTO to entity and vice versa, with this we also obtain cleaner code.
* We have also used an exception resolver (HandlerException) is a component that handles exceptions thrown by handlers. This component provides a way to customize exception handling.

## Project Configuration

1. **Database Configuration (application.properties)**
    ```bash 
    spring.datasource.username=your_username
    spring.datasource.password=your_password

2. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/bcnc-inditex.git
   cd bcnc-inditex

3. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run

4. **Run Tests:**
   ```bash
   ./mvnw test

# PricesController

The `PricesController` is a Spring MVC controller responsible for handling HTTP requests related to pricing information. It uses the `PricesService` to perform business logic and retrieve pricing data.

## Endpoints

| Endpoint             | Method | Description                                               | Request Body                                                      | Response                |
| -------------------- | ------ | --------------------------------------------------------- |-------------------------------------------------------------------| ----------------------- |
| `GET /`              | GET    | Retrieves a list of all prices.                           | N/A                                                               | List of `PricesDTO`     |
| `GET /filter`        | GET    | Retrieves pricing information based on filtering criteria. | `PricesRequestDTO (requestDate, productId, brandId)` (validated)  | `PricesDTO`             |

## Usage

1. **Get All Prices:**
   - Endpoint: `GET /`
   - Example: `curl -X GET http://localhost:8080/`

2. **Filter Prices by Start Date, Product ID, and Brand ID:**
   - Endpoint: `GET /filter`
   - Request Body: JSON object containing request date, product ID (optional), and brand ID (optional).
   - Example:
     ```json
     {
       "requestDate": "2022-01-01T00:00:00"
     }
     ```
     ```json
     {
       "requestDate": "2022-01-01T00:00:00",
       "productId": 35455,
       "brandId": 1
     }
     ```


- Example Request: `curl -X GET -H "Content-Type: application/json" -d '{"requestDate":"2022-01-01T00:00:00","productId":35455,"brandId":1}' http://localhost:8080/filter`

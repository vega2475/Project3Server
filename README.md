# Educational Project - Server Part

Welcome to the server part of our educational project! This component serves as a simulated weather sensor infrastructure. It generates daily weather measurements, including temperature and precipitation status. The client part retrieves this simulated data in JSON format, enabling the creation of temperature charts for each day.

## Project Description

The server part of the project is developed using Java 20 and employs various technologies for efficient data management and communication with the client part. Notable technologies include:

- **Java 20:** The core programming language used for building the server application.
- **Hibernate:** An object-relational mapping (ORM) framework for interacting with the database.
- **Spring Data JPA:** A Spring framework module simplifying data access using JPA.
- **Spring REST:** A component of Spring enabling the creation of RESTful APIs.
- **Maven:** A build automation and project management tool for dependency management and building.
- **PostgreSQL:** A powerful open-source relational database system.
- **SQL:** The standard language for relational database management and querying.
- **Spring Boot:** A Spring framework extension simplifying Spring application development.
- **Spring Validator:** A validation framework for validating incoming data.
- **JSON:** A lightweight data interchange format used for communication.

## API Description

The server part exposes a RESTful API that allows clients to retrieve the simulated weather data. The API provides endpoints for accessing daily temperature and precipitation status. Clients can make HTTP requests to these endpoints to retrieve the weather data in JSON format.

## Getting Started

To begin with the server part of the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the server project in your preferred Java IDE.
3. Configure the database connection in the application properties.
4. Build and run the project.

## Modern Spring Patterns

The server part of the project follows modern Spring patterns for organized and maintainable code. It uses DTOs (Data Transfer Objects) to define data structures exchanged between the client and server. Services manage business logic, while repositories handle database interactions.

Explore the source code to gain insights into how these patterns are implemented, creating a robust server application that simulates weather data and exposes a RESTful API.

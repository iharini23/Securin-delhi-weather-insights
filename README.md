# Securin Delhi Weather Insights

## Project Overview
Securin Delhi Weather Insights is a Spring Boot application developed to process and manage weather-related data for Delhi. The application reads data, performs necessary processing, and stores it in a database for further analysis and retrieval.

## Features
- CSV data processing
- Database integration
- Layered architecture (Controller, Service, Repository)
- Centralized exception handling
- RESTful API structure
- Clean and maintainable code structure

## Technology Stack
- Java
- Spring Boot
- Maven
- Spring Data JPA
- Hibernate
- MongoDB (or your configured database)

## Project Structure
- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic
- **Repository Layer** – Manages database operations
- **Entity Layer** – Defines entity classes
- **Util Package** – Contains global exception handling 
- **application.properties** – Configuration file

## Exception Handling
The application includes centralized exception handling implemented inside the `util` package.  
Custom exceptions and global exception handlers are used to ensure consistent error responses and better API reliability.

## How to Run the Project
1. Clone the repository:


git clone https://github.com/iharini23/Securin-delhi-weather-insights.git


2. Open the project in STS / IntelliJ IDEA.
3. Configure database details in `application.properties`.
4. Run the application as a Spring Boot Application.
5. Access the APIs using Postman or a browser.

## Future Enhancements
- API documentation using Swagger
- Enhanced validation mechanisms
- Unit and integration testing
- Deployment to cloud platform

## Author
Harini S

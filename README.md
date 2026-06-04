# Air Quality Monitoring System

![Static Badge](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=java)
![Static Badge](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge\&logo=springboot)
![Static Badge](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge\&logo=springsecurity)
![Static Badge](https://img.shields.io/badge/OAuth2-blue?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/Spring%20Data%20JPA-green?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/JavaDoc-red?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=docker)

## Description

This is a REST API developed in **Java** using **Spring Boot**.

The application allows citizens to consult the air quality of a city by consuming data from the **World Air Quality Index (WAQI)** API. It processes the AQI information and returns a customized response including pollution levels and health recommendations.

The system integrates **OAuth2 authentication**, stores queried data using **Spring Data JPA**, and follows best practices for secure API token management through externalized configuration.

The project is documented using **JavaDoc**.

## Set Up

### Requirements

Before running the project, make sure you have the following software installed:

* Java JDK 21 (or compatible version)
* Maven 3.9+
* IDE (IntelliJ IDEA, Eclipse, or VS Code)
* Database (MySQL, PostgreSQL, H2, etc.)
* WAQI API Token

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Marc-Borrell/spring-pj7.git
```

2. Navigate to the project directory:

```bash
cd spring-airquality
```

3. Configure the application properties:

```properties
waqi.api.token=YOUR_API_TOKEN //or if you want you can use my token

spring.datasource.url=jdbc:mysql://localhost:3306/airquality
spring.datasource.username=root
spring.datasource.password=password
```

4. Install dependencies and build the project:

```bash
mvn clean install
```

5. Run the application:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Features

* Air quality consultation by city.
* Integration with the WAQI external API.
* AQI level classification.
* Personalized health recommendations.
* OAuth2 authentication.
* Custom error handling.
* Data persistence with Spring Data JPA.
* Secure API token management using @Value.
* JavaDoc documentation.


## Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Security
* OAuth2
* Spring Data JPA
* Lombok
* Maven
* REST Client (RestClient / RestTemplate)
* JavaDoc
* Docker

## DEMO

### Air Quality Query

<img width="1918" height="959" alt="image" src="https://github.com/user-attachments/assets/6409f6aa-2869-42ea-9eda-48b7bfc43bea" />


```text
GET /api/v1/aire/barcelona
```

### Successful Response

<img width="1915" height="961" alt="image" src="https://github.com/user-attachments/assets/d6fa7267-ecca-49dc-98b5-2c8e6ffa823a" />


### Custom Error Response

<img width="1915" height="959" alt="image" src="https://github.com/user-attachments/assets/878cc923-77bf-45bb-a234-ee5ee72952f0" />


### User dashboard

<img width="1913" height="962" alt="image" src="https://github.com/user-attachments/assets/e9457058-6b11-4f06-b8ff-632a314b3348" />

## Project started from Docker

<img width="1524" height="198" alt="image" src="https://github.com/user-attachments/assets/6fcac618-0801-43c8-913e-68ccb50f6c90" />




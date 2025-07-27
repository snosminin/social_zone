# Simple social network project based on docker containers

## Introduction

This project utilizes a microservices architecture to provide a flexible and maintainable platform.

## Microservices Architecture

This project is structured around a collection of interconnected microservices, each responsible for managing distinct business processes. A summary of each service follows:

### 1. API Gateway

- **Description**:  This entry point directs all client requests to their designated microservices.
- **Technology**: Spring Cloud Gateway


### 2. Discovery Server

- **Description**: Helps the dynamic finding and enrollment of microservices throughout the system.
- **Technology**: Eureka Server

### 3. Posting Service

- **Description**: 
- **Technology**: Spring Boot
- **Features**: Rest API accepts requests and places them into the message broker

### 4. Store Service

- **Description**: 
- **Technology**: Spring Boot, Hibernate
- **Features**: Get data from Kafka broker and puts them to database


## Technologies

- **Spring Boot**
- **Apache Kafka**
- **PostgreSQL**
- **Spring Cloud**
- **Docker**


## ️ **Requirements**

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)


#### Build and Start All Services

Open the project's root directory in console:

```
docker-compose up
```
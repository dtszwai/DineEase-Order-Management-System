# DineEase Order & Management System

## Description

The Order Management System is developed using Java, Spring Boot, and SQL. It provides a comprehensive suite of APIs for managing orders, employees, customers, products, and categories.

## Features

- **Employee Management**: Comprehensive APIs for employee management tasks such as login, listing, creating, updating,
  and deleting employees. Admin authorization is handled using JWT.
- **Order Management**: Robust APIs for order management tasks such as submitting orders and listing all orders for a specific client.
- **Customer Management**: APIs for creating new customers and generating order tokens. Customers with a valid token can view the menu and submit their orders.
- **Product Management**: APIs for managing products, including creating, updating, and deleting products.
- **Category Management**: APIs for managing categories, including creating, updating, and deleting categories.

## Order Processing

Orders submitted by customers are received by the admin for processing. The admin has the ability to view, manage, and update the status of these orders.

## Technologies Used

- **Java**: The core programming language used for developing the project.
- **Spring Boot**: A powerful framework used for creating the RESTful APIs.
- **PostgreSQL**: A powerful open-source relational database system used for storing data.
- **Mybatis**: A persistence framework used for mapping Java objects to SQL statements.

## API Documentation

The API documentation is generated using Swagger and can be accessed at `/swagger-ui.html` on your local server after the application is running.

## Database Schema

The database schema includes tables for products, categories, employees, customers, and orders. Each product has a name, description, price, status, display order, category ID, and image URL. The schema is designed to be flexible and scalable to accommodate future enhancements.

Some data is preloaded into the database to facilitate testing and development.
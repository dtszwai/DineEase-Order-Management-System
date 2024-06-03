# DineEase Order & Management System

## Description

DineEase Order & Management System is a comprehensive application developed to simplify the ordering process for customers and management tasks for employees. The system is built with Java Spring Boot, and PostgreSQL, and a responsive frontend using Next.js and React. It includes a suite of APIs for managing orders, employees, customers, products, and categories, providing a seamless and efficient user experience.

## Features

### Backend (Server)

- **Employee Management**: Comprehensive APIs for tasks such as login, listing, creating, updating, and deleting employees. Admin authorization is handled using JWT.
- **Order Management**: APIs for submitting orders and listing all orders for a specific client.
- **Customer Management**: APIs for creating new customers and generating order tokens. Customers with a valid token can view the menu and submit their orders.
- **Product Management**: APIs for managing products, including creating, updating, and deleting products.
- **Category Management**: APIs for managing categories, including creating, updating, and deleting categories.

### Frontend (Web)

- **Customer Interface**: Allows customers to view the menu, place orders, and view their order history.
- **Employee Dashboard**: Enables employees to create/update categories and products, view and manage orders, and assign customers to tables.

## Technologies Used

### Backend

- **Java**: Core programming language.
- **Spring Boot**: Framework for creating RESTful APIs.
- **PostgreSQL**: Open-source relational database system.
- **MyBatis**: Persistence framework for mapping Java objects to SQL statements.

### Frontend

- **TypeScript**: Strongly typed programming language.
- **Next.js**: Framework for server-rendered React applications.
- **React**: Library for building user interfaces.

## API Documentation

API documentation is generated using Swagger and can be accessed at `/swagger-ui.html` on your local server after the backend application is running.

## Database Schema

The database schema includes tables for products, categories, employees, customers, and orders. Each product has a name, description, price, status, display order, category ID, and image URL. The schema is designed to be flexible and scalable to accommodate future enhancements.

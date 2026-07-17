# Smart Inventory and Order Management System

This is my Java Full Stack project developed using Spring Boot. The main purpose of this project is to manage products, inventory, customers, suppliers, and orders in one system. It also includes secure login using JWT authentication and role-based access.

## Features

- User Registration and Login
- JWT Authentication
- Role-Based Authorization (Admin & User)
- Product Management
- Category Management
- Supplier Management
- Customer Management
- Inventory Management
- Order Management
- Order Item Management
- Input Validation
- Global Exception Handling
- Swagger API Documentation

## Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- Oracle Database
- JWT
- Maven
- Swagger OpenAPI
- Lombok
- ModelMapper
- JUnit 5
- Mockito

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── config
 ├── filter
 ├── exception
 └── resources
```

## API Documentation

After running the project, open:

```
http://localhost:8080/swagger-ui/index.html
```

All REST APIs can be tested using Swagger UI.

## Authentication

This project uses JWT authentication.

1. Register a new user.
2. Login with your email and password.
3. Copy the generated JWT token.
4. Click the **Authorize** button in Swagger.
5. Enter the token in this format:

```
Bearer your_jwt_token
```

Now you can access the secured APIs.

## Database

- Oracle Database
- Spring Data JPA
- Hibernate ORM

## Future Improvements

- Email Notification
- Dashboard with Reports
- File Upload Support
- Docker Deployment
- Cloud Deployment

## Developed By

**Rutvik Raut**

Java Full Stack Developer (Fresher)

---

### Note

This project was developed as a learning project to improve my knowledge of Java Full Stack Development, Spring Boot, REST APIs, Spring Security, JWT Authentication, Oracle Database, and Git/GitHub.

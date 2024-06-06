# 📚 Online Book Store

Welcome to the Online Book Store project! This application is designed to provide a seamless and efficient way for users to browse, select, and purchase books online. The project addresses common challenges of online book shopping by offering a comprehensive and user-friendly platform.

## 🛠️ Technologies and Tools Used

This project leverages a range of modern technologies and tools to ensure robust performance and security:

- **Spring Boot**: For building the backend of the application.
- **Spring Security**: To handle authentication and authorization.
- **Spring Data JPA**: For database operations.
- **Swagger**: To document and test the APIs.
- **MySQL**: As the relational database.
- **Maven**: For project build and dependency management.

## 🌟 Features

### User Functionalities 🥺

- **User Registration and Authentication**: Users can sign up and log in to access the store.
- **Book Browsing and Searching**: Users can view all books, search for specific books by name, and explore books by category.
- **Shopping Cart Management**: Users can add books to their cart, view their cart, and remove books from the cart.
- **Order Placement and Review**: Users can place orders for books in their cart, view past orders, and see details of specific orders.

### Admin Functionalities 😊

- **Book Management**: Admins can add new books, update book details, and remove books from the store.
- **Category Management**: Admins can create new categories, update category details, and delete categories.
- **Order Management**: Admins can update the status of orders (e.g., "DELIVERED", "COMPLETED").

## 🚀 Setup Instructions 🧙‍♂️🔮

To set up the project locally, follow these steps:

### Prerequisites

Ensure you have the following software installed:

- Java 21
- Maven 3.9.5
- MySQL

### Database Configuration

1. Ensure MySQL is installed and running.
2. Create a database named `online_book_store`.
3. Update the database configurations in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/online_book_store
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
## Environment Variables
Create a .env file in the root directory of your project with the following content:

MYSQLDB_DATABASE=your_database_name  
MYSQLDB_USER=your_user_name  
MYSQLDB_ROOT_PASSWORD=your_root_password  
MYSQLDB_LOCAL_PORT=your_local_port    
MYSQLDB_DOCKER_PORT=your_docker_port  

SPRING_LOCAL_PORT=your_local_port  
SPRING_DOCKER_PORT=your_docker_port  
DEBUG_PORT=your_debug_port  

JWT_SECRET=your_jwt_secret  
JWT_EXPIRATION=your_expiration_time  

## Building and Running the Project ⚙️

1. - **Clone the repository**:
  ```bash
git clone https://github.com/slizko1/Online-Book-Store.git
cd Online-Book-Store
  ```

2. - **Build the project**:
  ```bash
   mvn clean package
  ```

3. **Build and start Docker containers**:
   ```bash
    docker-compose down --rmi all
   ```
   You can use this command for remove all unnecessary images
   ```bash
   docker-compose up --build   
    ```
   This command for build new images and run app in docker

### Accessing the Application

- Web Application: Open your browser and go to http://localhost:8080 to access the web application.
- Swagger UI: The API documentation is available at http://localhost:8080/swagger-ui.html.

### Authentication

The application uses JWT for secured access. Use the following credentials to log in as an admin:

    Login: admin@mail.com
    Password: 123456

## 📬 Postman Collection 📨
To facilitate API testing, a Postman collection is provided. You can import this collection into Postman to test the various endpoints.

Download Postman Collection: [ Postman Collection Link](https://www.postman.com/joint-operations-cosmologist-51875172/workspace/online-book-store)

## 🛠️ Challenges and Solutions

### Challenges:
Data Integrity: Ensuring data consistency across different parts of the application.  
Security: Implementing robust security measures to protect user data and transactions.  
Scalability: Designing the system to handle a growing number of users and transactions.  

### Solutions:
Spring Data JPA: Used for managing database interactions and ensuring data integrity.  
Spring Security: Integrated to manage authentication and authorization.  
Modular Design: Adopted a modular approach to easily scale different parts of the application independently.  

## Conclusion 🏁
The Online Book Store provides a comprehensive solution for users to manage their book shopping experience and for administrators to manage the store's inventory and orders. The project leverages robust technologies and tools to ensure a secure and scalable platform. I welcome your feedback and contributions to further enhance this application.

Feel free to reach out if you have any questions or need further assistance. Happy coding! 😊


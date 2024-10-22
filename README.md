
# ToDo List Spring Boot Application

Welcome to the **ToDo List Application**! This is a web-based application built using Java and Spring Boot. It provides a RESTful API for managing to-do tasks, user authentication with Spring Security, and a PostgreSQL database. The project also includes Swagger for API documentation and a graphical interface.

## 🚀 Features

- **User Authentication**: Secure user login using Spring Security.
- **Task Management**: Create, update, and delete tasks with ease.
- **RESTful API**: Full RESTful support with Spring Boot.
- **Swagger Documentation**: Comprehensive API documentation with Swagger.
- **Database Support**: Uses PostgreSQL (for deployment) and H2 (for local testing).

## 📸 Screenshots

![Showcase of user-controller and task-controller](image.png)

![Showcase of GET method for all Tasks by the logged user](image-1.png)

![Showcase of POST method for a User](image-2.png)

## 🛠 Installation

### Prerequisites

Ensure you have the following installed:
- **Java 17** or newer
- **Gradle**

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/fornari03/todo-list-spring-boot.git
    ```

2. **Switch to the desired branch**:
    - Open the project.
    - For local testing: `local-working` branch with H2 database.
    - For deployment: `main` branch (though the Railway deployment currently has issues).
  
    ```bash
    git checkout local-working
    ```

3. **Run the application**:

    Run the Java file `ToDoListSpringApplication.java`.


## 📚 Usage

### Local Environment (Branch: `local-working`)

- After running the application, open your browser and navigate to:
    ```
    http://localhost:8080/login
    ```

- A default **admin** user is created for testing purposes:
    - **Username**: `admin`
    - **Password**: `123`

- This user can create other users, as well as manage tasks. Explore the functionality by adding tasks and updating and deleting them.

### Production Environment (Branch: `main`)

- In the `main` branch, the project is set up to connect to a **PostgreSQL** database hosted on **Railway**. However, due to an issue with the Railway container (dies immediately after starting), the deployment is currently not working, though the database connection itself works. Feel free to check the logs for debugging purposes.

    ### [Link to the Railway Project](https://railway.app/project/44c03f4d-4460-460c-b54b-5996741b7104/service/8c45850e-f95f-4817-b047-26d0ba9fa9ef)

## 💡 Branches Overview

- **main**: 
  - Aims for production deployment using **PostgreSQL** on Railway.
  - PostgreSQL database configured for remote connection.
  - Note: Railway deployment issue (container crash after startup).

- **local-working**:
  - Runs locally for testing with **H2 in-memory database**.
  - Swagger available at: `http://localhost:8080/swagger-ui/`.
  - URL for login: `http://localhost:8080/login`.
  - Admin credentials:
  
    `Username: admin`

    `Password: 123`

## 🛠 Technology Stack

- **Java** (Spring Boot framework)
- **Spring Security** (for authentication)
- **Swagger** (for API documentation)
- **PostgreSQL** (main branch database)
- **H2 Database** (local testing)
- **Gradle** (for build management)

## 💡 Contributing

We welcome contributions! Follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feat-branch`).
3. Make your changes and commit (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Thank you for using our ToDo List Application! 🎉
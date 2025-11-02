# Loan Management System

A comprehensive RESTful API for managing loan applications, from initial request to final payment.

## Features

*   **User Authentication:** Secure registration and login for customers, loan officers, and admins.
*   **Loan Application:** Customers can apply for various types of loans (Personal, Home, Car, etc.).
*   **Document Upload:** Attach necessary documents to loan applications.
*   **Loan Processing:** Loan officers can review applications, and approve or reject them.
*   **Loan Management:** Track loan status, payments, and remaining balances.
*   **Payment Processing:** Record payments made towards active loans.

## Technologies Used

*   **Java 24**
*   **Spring Boot 3.5.5**
*   **Spring Security:** For authentication and authorization.
*   **Spring Data JPA:** For database interaction.
*   **MySQL:** As the relational database.
*   **Lombok:** To reduce boilerplate code.
*   **MapStruct:** For mapping between DTOs and entities.
*   **Maven:** For dependency management.

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/loanmanagement.git
    cd loanmanagement
    ```

2.  **Install dependencies:**
    ```bash
    mvn install
    ```

## Configuration

Update the `src/main/resources/application.properties` file with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/loanmanagement?createDatabaseIfNotExist=true
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

## API Endpoints

### Authentication

*   `POST /api/auth/register`: Register a new user.
*   `POST /api/auth/login`: Authenticate a user and receive a JWT token.

### Loan Applications

*   `POST /api/loan-applications`: Submit a new loan application.
*   `GET /api/loan-applications/{id}`: Get details of a specific loan application.
*   `PUT /api/loan-applications/{id}`: Update a loan application (for loan officers).

### Loans

*   `GET /api/loans`: Get a list of all loans.
*   `GET /api/loans/{id}`: Get details of a specific loan.

### Payments

*   `POST /api/payments`: Make a payment towards a loan.

## Running the Application

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## Future Improvements

*   Implement JWT token refresh.
*   Add more comprehensive unit and integration tests.
*   Implement email notifications for application status changes.
*   Add a frontend application.

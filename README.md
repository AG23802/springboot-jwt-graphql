# Spring Boot JWT GraphQL

This project is the secure backend API for a full-stack application. It is built using **Spring Boot** and **Java** and serves as a comprehensive example of a modern, professional API. The application provides a secure **GraphQL API** with **JWT authentication** and uses **JPA** to interact with a **PostgreSQL** database. It is fully containerized with **Docker** and includes a **CI/CD** pipeline to demonstrate a complete development workflow.

---

## Key Features

### Secure Authentication & APIs

- **JWT-based Authentication:** The API is secured using **Spring Security** to enforce authentication on all protected endpoints. This implementation uses **JSON Web Tokens (JWTs)**, which allows the application to be stateless and highly scalable. Each token is a self-contained unit that verifies the user's identity without requiring a database lookup for every request.
- **GraphQL API:** This project leverages GraphQL to provide a flexible and efficient API layer. Unlike traditional REST, GraphQL allows the client to request exactly the data it needs, which reduces over-fetching and improves performance. The API includes both **queries** (to retrieve data) and **mutations** (to modify data).
- **REST Endpoints + Swagger UI:** Alongside GraphQL, the project includes standard **REST APIs** for authentication and utility endpoints. These are fully documented and browsable through **Swagger UI**:
  - Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
  - OpenAPI Docs: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **API Versioning:** REST endpoints are versioned using a simple, scalable strategy. For example:
  - `http://localhost:8080/api/v1/...`
  - `http://localhost:8080/api/v2/...`  
  This ensures backward compatibility as the API evolves and new versions are introduced.
- **Token Management:** A complete authentication flow is implemented, from user login to **refresh token** generation. This allows the frontend to maintain a continuous, secure session without requiring the user to re-enter their credentials.

### Data Management

- **JPA and Spring Data JPA:** The application uses **JPA (Java Persistence API)** for Object-Relational Mapping (ORM), which simplifies database interactions. **Spring Data JPA** further streamlines this process by automatically generating repository methods, drastically reducing boilerplate code.
- **PostgreSQL Database:** The backend is configured to connect to a **PostgreSQL** database, a popular choice for production environments due to its reliability and robustness.
- **Complex Queries:** The project demonstrates proficiency with various query types, including standard JPA methods, JPQL, native SQL, and even stored procedures, showcasing a versatile approach to data retrieval.

### DevOps

- **Docker Integration:** The entire application is containerized with **Docker**. This ensures a consistent environment across development, testing, and production, eliminating "works on my machine" issues. The Dockerfile simplifies the build and deployment process.
- **CI/CD Pipeline:** A **GitHub Actions** workflow is included to automate the build, test, and containerization processes every time code is pushed. This demonstrates a professional approach to continuous integration and continuous delivery.

---

## Getting Started

1. Clone the repository to your local machine.
2. Set up a PostgreSQL database and update the database configuration in `src/main/resources/application.properties`.
3. Build the project using your build tool (e.g., Maven or Gradle) to resolve all dependencies.
4. Run the application. The API will be available on [http://localhost:8080](http://localhost:8080).

---
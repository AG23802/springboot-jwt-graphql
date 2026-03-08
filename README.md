# Fruitly (Cloud-Native API)

This is a production-ready backend API built with **Spring Boot** and **Java**. It features a secure **GraphQL API**, **JWT Authentication**, and a fully automated **Azure Cloud** infrastructure.

---

## 🚀 Key Features

### Secure API & Authentication
* **GraphQL & REST:** Hybrid API design. **GraphQL** for flexible data fetching and **Versioned REST** (`/api/v1/`) for authentication and utility.
* **JWT Security:** Stateless authentication using **Spring Security** and JSON Web Tokens, including **Refresh Token** flows.
* **Self-Documenting:** Integrated **Swagger UI** and OpenAPI 3.0 for real-time API testing at `/swagger-ui/index.html`.

### Data Architecture
* **Persistence:** **Spring Data JPA** with **PostgreSQL** for robust, scalable data management.
* **Advanced Querying:** Implementation of JPQL, Native SQL, and Stored Procedures to handle complex data requirements.

### Azure DevOps & Security
* **CI/CD Pipeline:** Automated **GitHub Actions** using **OIDC** to build and deploy to **Azure Container Apps (ACA)**.
* **Zero-Trust Identity:** Utilizes **Azure Managed Identity** to authenticate between services without hardcoded passwords.

* **Secret Management:** **Azure Key Vault** integration via **Spring Cloud Azure** to externalize sensitive credentials (DB passwords, JWT keys).
* **Infrastructure as Code:** Environment-aware configuration using **Spring Profiles** (`dev`/`prod`) and Azure Environment Variables.


---

## 🛠️ Tech Stack
* **Language:** Java 17+ / Spring Boot 3.x
* **API:** GraphQL, Spring Web, SpringDoc (Swagger)
* **Security:** Spring Security, JWT
* **Database:** PostgreSQL, Hibernate/JPA
* **Cloud:** Azure Container Apps, Key Vault, Container Registry
* **DevOps:** GitHub Actions, Cloud Native Buildpacks (No-Dockerfile deployment)

---

## 🏃 Quick Start

### Profiles
Activate a profile using the following commands:
* **Development:** `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`
* **Production:** `./mvnw spring-boot:run -Dspring-boot.run.profiles=prod`

### Endpoints
* **GraphQL Interface:** `/graphql`
* **Swagger UI:** `/swagger-ui/index.html`
* **OpenAPI Docs:** `/v3/api-docs`
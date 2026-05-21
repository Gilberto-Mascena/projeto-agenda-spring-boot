[English](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README.md) |
[Português Brasileiro](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README-pt_br.md)

# Spring Boot Agenda Project

#### A professional and robust REST API for contact management, developed with Spring Boot 3.x, focused on market best practices, security, and containerization.

## 🚀 Features

- **Contact Registration**: Allows adding new contacts to the address book with data validation (`spring-boot-starter-validation`).

- **Contact Query**: Complete listing or refined search by ID.

- **Contact Update**: Secure update flow using structured DTOs such as *Java Records* and Mappers.

- **Contact Deletion**: Physical removal of records from the database.

## 🛠️ Technologies and Tools Used

- **Java 21**: Using the most modern features of the language, such as *Records*.

- **Spring Boot 3.5.0**: Main ecosystem for the development of the REST API.

- **PostgreSQL**: Official relational database used in development and production environments.

- **H2 Database**: Isolated in-memory database exclusively for fast execution of automated tests with **JUnit 5**.

- **Spring Data JPA (Hibernate)**: For abstraction of the persistence layer and object-relational mapping.

- **Docker & Docker Compose**: For orchestration and fast initialization of the database container.

- **Java Dotenv (`cdimascio`)**: For secure management of credentials and environment variables in `.env` files.

- **Swagger (Springdoc OpenAPI)**: For interactive documentation and visual testing of API endpoints.

## 📁 Featured Folder Structure

The project has been restructured following software design conventions:

- `src/main/java/.../exceptions/`: Centralization of global error handling (`@RestControllerAdvice`).

- Isolated mapping between Entities and Records to ensure that sensitive data is not unnecessarily exposed in the request/response.

## 🏁 How to Run the Project

### Prerequisites

- **Java 21** installed.

- **Docker** and **Docker Compose** installed and running on the machine.

- **Maven** for dependency management (or use the included `mvnw`).

### Step by Step

1. **Clone the repository:**

```bash
git clone git@github.com:Gilberto-Mascena/projeto-agenda-spring-boot.git
cd projeto-agenda-spring-boot
```

2. **Configure the Environment Variables:**
   Since the credentials are protected, we created an example file. Copy the .env.example file, creating your actual .env file in the project root, and fill it with your local configurations (the keys must match the Spring and Docker properties):

```bash
cp .env.example .env
```
3. **Start the Database (Docker):**

With Docker running, initialize the PostgreSQL container in the background:
```bash
docker compose up -d
```
4. **Compile and Run the Application:**

```bash
mvn clean install
mvn spring-boot:run
```
5. **Access the Application and Documentation:**

- The API will be running locally at: `http://localhost:8080`

- To visually test the endpoints, access the Swagger UI at: `http://localhost:8080/swagger-ui/index.html`

- To test the functionalities, You can use Postman or any other HTTP client. [Postman Collection](/docs/postman/CRUD%20agenda.postman_collection.json) available for import.

### Postman Screenshot

![postman](/docs/imgs/screenshot-postman.png)
----
![front-end](/docs/imgs/front-angular.png)

### Contribution

If you want to contribute to the project, follow these steps:\
Fork the repository.\
Create a branch with your changes: git checkout -b my-change.\
Make the changes.\
Submit a pull request with a detailed description of the changes.

## 📜 *License*

*This project is licensed under the MIT License. See more details at:* [_LICENSE.md_](/LICENSE.md)

### Gilberto | Dev _2025_
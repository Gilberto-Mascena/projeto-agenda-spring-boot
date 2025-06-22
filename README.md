[English](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README.md) |
[Português Brasileiro](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README-pt_br.md)

# Spring Boot Agenda Project

#### This is a project for a simple contact agenda application developed with Spring Boot. The application allows you to perform basic CRUD operations (Create, Read, Update, Delete) to manage contacts.

## Features

- **Register Contact**: Allows you to add new contacts to the agenda.
- **Search Contacts**: Allows you to search for all registered contacts or search by ID.
- **Update Contact**: Allows you to update the data of an existing contact.
- **Delete Contact**: Allows you to delete a contact from the agenda.

## Technologies Used

- **Spring Boot**: Framework for rapid development of Java applications.
- **H2**: In-memory database used for testing.
- **Postgres**: Relational database used in production.
- **JPA (Hibernate)**: For interaction with the relational database. - **Postman**: For API testing and simulating HTTP
  requests.
- **Swagger**: For API documentation and interactive testing.

## How to Run the Project

### Prerequisites

- **Java 11+** or higher.
- **Maven** installed for dependency management.

### Steps to run

1. Clone the repository:

```bash
    git@github.com:Gilberto-Mascena/projeto-agenda-spring-boot.git
    cd projeto-agenda-springboot
```

2. Run the project using Maven:

```bash
    mvn clean install
```

```bash
    mvn spring-boot:run
```
3. The application will be running at http://localhost:8080.

4. API documentation is available at http://localhost:8080/swagger-ui.html.
5. To test the functionality, you can use Postman or any other HTTP
   client. [Postman Collection](/docs/imgs/CRUD%20agenda.postman_collection.json)

### Postman Screenshot

![postman](/docs/imgs/screenshot-postman.png)
----
![front-end](/docs/imgs/front-angular.png)

### Access the H2 Console

H2 is configured to run in memory during testing, and the web console is available at:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb\
Username: sa\
Password: (leave blank)

### Project Structure

``` plaintext
├── docs
│   └── imgs
│       ├── CRUD-agenda.postman_collection.json
│       ├── front-angular.png
│       └── screenshot-postman.png
├── HELP.md
├── LICENSE.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── README-en.md
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── com
    │   │           └── mascenadev
    │   │               └── projetoagendaspringboot
    │   │                   ├── config
    │   │                   │   ├── CorsConfig.java
    │   │                   │   └── SwaggerConfig.java
    │   │                   ├── controller
    │   │                   │   └── ContactController.java
    │   │                   ├── dtos
    │   │                   │   ├── ContactRequestDTO.java
    │   │                   │   └── ContactResponseDTO.java
    │   │                   ├── entities
    │   │                   │   └── Contact.java
    │   │                   ├── exception
    │   │                   │   ├── ContactNotFoundException.java
    │   │                   │   ├── ErrorResponse.java
    │   │                   │   └── GlobalExceptionHandler.java
    │   │                   ├── ProjetoAgendaSpringBootApplication.java
    │   │                   ├── repository
    │   │                   │   └── ContactRepository.java
    │   │                   └── service
    │   │                       └── ContactService.java
    │   └── resources
    │       ├── application-dev.yaml
    │       ├── application-prod.yaml
    │       ├── application-test.yaml
    │       ├── application.yaml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── br
                └── com
                    └── mascenadev
                        └── projetoagendaspringboot
                            └── ProjetoAgendaSpringBootApplicationTests.java

```

### Contribution

If you want to contribute to the project, follow these steps:\
Fork the repository.\
Create a branch with your changes: git checkout -b my-change.\
Make the changes.\
Submit a pull request with a detailed description of the changes.

## 📜 *License*

*This project is licensed under the MIT License. See more details at:* [_LICENSE.md_](/LICENSE.md)

### Gilberto | Dev _2025_

### README Explanation:

1. **Introduction**: Explains the project's objective and main features.
2. **Technologies Used**: Lists the technologies that were used in the project.
3. **How to Run the Project**: Steps to run the project in your local environment, including the use of Maven.
4. **Accessing the H2 Console**: Provides details on how to access the H2 database for monitoring and testing
   directly.
5. **Project Structure**: Description of the project directory structure.
6. **Contribution and License**: How to contribute to the project and license under which the code is available.

This README will provide a clear and practical overview of how to run, test, and contribute to the project.
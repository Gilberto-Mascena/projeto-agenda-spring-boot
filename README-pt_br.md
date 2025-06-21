[Português Brasileiro](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README-pt_br.md) |
[English](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README.md)

# Projeto Agenda Spring Boot

#### Este é um projeto de uma aplicação simples de agenda de contatos desenvolvida com _Spring
Boot_. A aplicação permite realizar operações básicas de CRUD (Create, Read, Update, Delete) para gerenciar contatos.

## Funcionalidades

- **Cadastrar Contato**: Permite adicionar novos contatos à agenda.
- **Buscar Contatos**: Permite buscar todos os contatos cadastrados ou buscar por ID.
- **Atualizar Contato**: Permite atualizar os dados de um contato existente.
- **Excluir Contato**: Permite excluir um contato da agenda.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento rápido de aplicações Java.
- **H2**: Banco de dados em memória utilizado para testes.
- **Postgres**: Banco de dados relacional utilizado em produção.
- **JPA (Hibernate)**: Para interação com o banco de dados relacional.
- **Postman**: Para testes de API e simulação de requisições HTTP.
- **Swagger**: Para documentação da API e testes interativos.

## Como Executar o Projeto

### Pré-requisitos

- **Java 11+** ou versão superior.
- **Maven** instalado para gerenciamento de dependências.

### Passos para executar

1. Clone o repositório:

   ```bash
   git@github.com:Gilberto-Mascena/projeto-agenda-spring-boot.git

2. Navegue até o diretório do projeto:

    ```bash
    cd projeto-agenda-springboot

3. Execute o projeto usando Maven:

    ```bash
    mvn spring-boot:run

4. A aplicação estará rodando em http://localhost:8080.
5. Documentaação da API está disponível em http://localhost:8080/swagger-ui.html.
6. Para testar as funcionalidades, você pode usar o Postman ou qualquer outro cliente
   HTTP. [Collection do Postman](/docs/imgs/CRUD%20agenda.postman_collection.json)

### Imagem do Postman

![postman](/docs/imgs/screenshot-postman.png)
----
![front-end](/docs/imgs/front-angular.png)

### Acessar o Console H2

O H2 está configurado para rodar em memória durante os testes, e o console web está disponível em:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb\
Username: sa\
Password: (deixe em branco)

### Estrutura do Projeto

``` plaintext
├── docs
│   └── imgs
│       ├── CRUD agenda.postman_collection.json
│       ├── front-angular.png
│       └── screenshot-postman.png
├── HELP.md
├── LICENSE.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── README-pt_br.md
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
    │   │                   │   └── ContatoController.java
    │   │                   ├── dtos
    │   │                   │   ├── ContatoRequestDTO.java
    │   │                   │   └── ContatoResponseDTO.java
    │   │                   ├── entities
    │   │                   │   └── Contato.java
    │   │                   ├── exception
    │   │                   │   ├── ContatoNaoEncontradoException.java
    │   │                   │   ├── ErroResponse.java
    │   │                   │   └── GlobalExceptionHandler.java
    │   │                   ├── ProjetoAgendaSpringBootApplication.java
    │   │                   ├── repository
    │   │                   │   └── ContatoRepository.java
    │   │                   └── service
    │   │                       └── ContatoService.java
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

### Contribuição

Se você deseja contribuir para o projeto, siga os seguintes passos:\
Faça um fork do repositório.\
Crie uma branch com suas alterações: git checkout -b minha-alteracao.\
Realize as modificações.\
Envie um pull request com uma descrição detalhada das alterações.

## 📜 *Licença*

*Este projeto é licenciado sob a Licença MIT. Veja mais detalhes em:* [_LICENSE.md_](/LICENSE.md)

### Gilberto | Dev _2025_

### Explicação do README:

1. **Introdução**: Explica o objetivo do projeto e as funcionalidades principais.
2. **Tecnologias Utilizadas**: Lista as tecnologias que foram usadas no projeto.
3. **Como Executar o Projeto**: Passos para rodar o projeto no seu ambiente local, incluindo o uso do Maven.
4. **Acessar o Console H2**: Fornece detalhes sobre como acessar o banco de dados H2 para monitorar e testar
   diretamente.
5. **Testes**: Explica como rodar os testes automatizados para garantir que a funcionalidade esteja funcionando
   corretamente.
6. **Estrutura do Projeto**: Descrição da estrutura de diretórios do projeto.
7. **Contribuição e Licença**: Como contribuir para o projeto e a licença sob a qual o código está disponível.

Esse README fornecerá uma visão geral clara e prática de como rodar, testar e contribuir para o projeto.


[Português Brasileiro](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README-pt_br.md) |
[English](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README.md)

# Projeto Agenda Spring Boot

#### Uma API REST profissional e robusta para gerenciamento de contatos, desenvolvida com Spring Boot 3.x, focada em boas práticas de mercado, segurança e conteinerização.

## 🚀 Funcionalidades

- **Cadastro de Contatos**: Permite adicionar novos contatos à agenda com validações de dados (`spring-boot-starter-validation`).
- **Consulta de Contatos**: Listagem completa ou busca refinada por ID.
- **Atualização de Contatos**: Fluxo seguro de atualização utilizando DTOs estruturados como *Java Records* e Mappers.
- **Exclusão de Contatos**: Remoção física de registros do banco de dados.

## 🛠️ Tecnologias e Ferramentas Utilizadas

- **Java 21**: Utilizando os recursos mais modernos da linguagem, como *Records*.
- **Spring Boot 3.5.0**: Ecossistema principal para o desenvolvimento da API REST.
- **PostgreSQL**: Banco de dados relacional oficial utilizado no ambiente de desenvolvimento e produção.
- **H2 Database**: Banco de dados em memória isolado exclusivamente para a execução veloz de testes automatizados com **JUnit 5**.
- **Spring Data JPA (Hibernate)**: Para abstração da camada de persistência e mapeamento objeto-relacional.
- **Docker & Docker Compose**: Para orquestração e inicialização rápida do container do banco de dados.
- **Java Dotenv (`cdimascio`)**: Para gerenciamento seguro de credenciais e variáveis de ambiente em arquivos `.env`.
- **Swagger (Springdoc OpenAPI)**: Para documentação interativa e testes dos endpoints da API de forma visual.

## 📁 Estrutura de Pastas de Destaque

O projeto foi reestruturado seguindo as convenções de design de software:
- `src/main/java/.../exceptions/`: Centralização de tratamentos de erros globais (`@RestControllerAdvice`).
- Mapeamento isolado entre Entidades e Records para garantir que dados sensíveis não sejam expostos desnecessariamente na requisição/resposta.

## 🏁 Como Executar o Projeto

### Prerrequisitos

- **Java 21** instalado.
- **Docker** e **Docker Compose** instalados e rodando na máquina.
- **Maven** para gerenciamento de dependências (ou utilize o `mvnw` incluso).

### Passo a Passo

1. **Clonar o repositório:**

```bash
git clone git@github.com:Gilberto-Mascena/projeto-agenda-spring-boot.git
cd projeto-agenda-spring-boot
```

2. **Configurar as Variáveis de Ambiente:**
Como as credenciais são protegidas, criamos um arquivo de exemplo. Copie o arquivo .env.example criando o seu arquivo .env real na raiz do projeto e preencha com as suas configurações locais (as chaves devem bater com as propriedades do Spring e do Docker):

```bash
cp .env.example .env
```
3. **Subir o Banco de Dados (Docker):**
   Com o Docker rodando, inicialize o container do PostgreSQL em segundo plano:
```bash
docker compose up -d
```
4. **Compilar e Executar a Aplicação:**

```bash
mvn clean install
mvn spring-boot:run
```
5. **Acessar a Aplicação e Documentação:**
   - A API estará rodando localmente em: `http://localhost:8080`
   - Para testar os endpoints visualmente, acesse o Swagger UI em: `http://localhost:8080/swagger-ui/index.html`
   - Para testar as funcionalidades, você pode usar o Postman ou qualquer outro cliente
      HTTP. [Collection do Postman](/docs/postman/CRUD%20agenda.postman_collection.json) disponível para importação.

### Imagem do Postman

![postman](/docs/imgs/screenshot-postman.png)
----
![front-end](/docs/imgs/front-angular.png)

### Contribuição

Se você deseja contribuir para o projeto, siga os seguintes passos:\
Faça um fork do repositório.\
Crie uma branch com suas alterações: git checkout -b minha-alteracao.\
Realize as modificações.\
Envie um pull request com uma descrição detalhada das alterações.

## 📜 *Licença*

*Este projeto é licenciado sob a Licença MIT. Veja mais detalhes em:* [_LICENSE.md_](/LICENSE.md)

### Gilberto | Dev _2025_


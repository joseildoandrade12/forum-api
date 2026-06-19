# Fórum API

API REST de um fórum simples, desenvolvida em Java com Spring Boot e PostgreSQL, seguindo arquitetura em camadas (Controller → Service → Repository → Model).

## 🚀 Tecnologias

- Java 21+
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Maven

## 📋 Funcionalidades

-  CRUD de Categorias
-  CRUD de Tópicos (vinculados a uma categoria)
-  CRUD de Respostas (vinculadas a um tópico)
-  Cadastro de usuário
-  Login com autenticação JWT
-  Tópicos e respostas vinculados ao usuário autenticado

## ⚙️ Como rodar o projeto localmente

### Pré-requisitos
- Java 21+
- Maven
- PostgreSQL rodando localmente (ou via Docker)

### Passo a passo

```bash
# Clone o repositório
git clone https://github.com/SEU-USUARIO/forum-api.git
cd forum-api

# Configure o banco de dados no application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forum_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha

# Rode a aplicação
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

## 📌 Endpoints

### Categorias
| Método | Endpoint            | Descrição                  |
|--------|----------------------|------------------------------|
| GET    | `/categorias`         | Lista todas as categorias    |
| GET    | `/categorias/{id}`    | Busca categoria por id       |
| POST   | `/categorias`         | Cria uma nova categoria      |
| PUT    | `/categorias/{id}`    | Atualiza uma categoria       |
| DELETE | `/categorias/{id}`    | Remove uma categoria         |

### Tópicos
| Método | Endpoint            | Descrição                  |
|--------|----------------------|------------------------------|
| GET    | `/topicos`            | Lista todos os tópicos       |
| GET    | `/topicos/{id}`       | Busca tópico por id          |
| POST   | `/topicos`             | Cria um novo tópico          |
| PUT    | `/topicos/{id}`        | Atualiza um tópico           |
| DELETE | `/topicos/{id}`        | Remove um tópico             |

### Respostas
| Método | Endpoint                      | Descrição                  |
|--------|---------------------------------|------------------------------|
| GET    | `/topicos/{id}/respostas`        | Lista respostas de um tópico |
| POST   | `/respostas`                     | Cria uma nova resposta       |
| PUT    | `/respostas/{id}`                | Atualiza uma resposta        |
| DELETE | `/respostas/{id}`                | Remove uma resposta          |

### Autenticação
| Método | Endpoint           | Descrição                          |
|--------|----------------------|--------------------------------------|
| POST   | `/auth/registrar`     | Cadastra um novo usuário             |
| POST   | `/auth/login`         | Autentica e retorna um token JWT     |

> Para os endpoints protegidos, envie o token no header:
> `Authorization: Bearer SEU_TOKEN`

## 🗺️ Roadmap do desenvolvimento

Projeto desenvolvido em fases, do CRUD mais simples até a autenticação:

1. Setup do projeto e conexão com o banco
2. CRUD de Categoria
3. CRUD de Tópico
4. CRUD de Resposta
5. Validações e tratamento de erros
6. Cadastro de usuário
7. Login com JWT

## 📄 Licença

Este projeto está sob a licença MIT.

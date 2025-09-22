  # Challenge Sprint 3

Este projeto é uma aplicação desenvolvida em **Java com Spring Boot**, utilizando **Maven** como gerenciador de dependências.  
Ele foi construído como parte de um desafio de sprint, com foco em práticas modernas de desenvolvimento web e persistência de dados.

## 🚀 Tecnologias utilizadas

- **Java 17+**
- **Spring Boot 3.5.6**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
- **Maven**
- **H2 Database** (ou outro banco relacional, se configurado)
- **JUnit / Mockito** (para testes)

## 📂 Estrutura do projeto

```
Challenge_Sprint_3/
 └── Challenge_Sprint_3/
     ├── Challenge/        # Código-fonte principal da aplicação
     ├── .git/             # Repositório Git
     └── HELP.md           # Documentação de referência do Spring Boot
```

## ⚙️ Como rodar o projeto

1. **Clonar o repositório:**
   ```bash
   git clone <url-do-repo>
   cd Challenge_Sprint_3
   ```

2. **Compilar e rodar:**
   ```bash
   mvn spring-boot:run
   ```

3. Acesse a aplicação no navegador:
   ```
   http://localhost:8080
   ```

## 🛠️ Endpoints principais

- `GET /` – Página inicial (renderizada com Thymeleaf).
- `API REST` – Endpoints protegidos por Spring Security.
- Persistência de dados configurada via JPA/Hibernate.

## ✅ Testes

Para rodar os testes automatizados:

```bash
mvn test
```

## 📖 Documentação de apoio

O arquivo [`HELP.md`](Challenge/HELP.md) contém links de referência para a documentação oficial do **Spring Boot** e seus módulos.

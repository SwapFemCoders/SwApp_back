<div align="center">
  <img src="../SwApp_back/assets/Logo.png" width="200";">
</div>


A web platform for clothing exchange between users, allowing them to post items, browse available clothing, and easily manage exchanges.

The project is built with a separate full-stack architecture:

* Frontend: React + Vite
* Backend: Spring Boot + PostgreSQL + Postman
* Communication: REST API with JWT authentication

## 💻 Requirements to run the application

You need to have the following installed:

| Tool | Use |
|------|-----|
|Node.js | Run the interface |
| Java 21 |  Run the server |
| Maven | Backend building |
| PostgreSQL | Database |

## 🛠️ Technologies used

### Backend

| Categories | Applications Used |
|------------|---------------------|
| Technology | Java 21, Sprint Boot 3, Visual Studio Code |
| Database | PostgreSQL, Postman |
| Build Tool | Apache Maven 3.9+ |
| Libraries | Lombok, JDBC |
| Test| JUnit, Vitest |
---

### Frontend

| Categories | Applications Used |
|------------|---------------------|
| Technology | React 19, Vite, Visual Studio Code |
| Management Tools | Jira, with Scrum methodology and Confluence |
| Design Tools | Figma and CSS Modules |
| Version Control Tools | Git/GitHub - Gitflow - conventional commits |
---

## 🏛 Architecture

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂swapp
 ┃ ┃ ┃ ┃ ┗ 📂swapp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CorsConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleRequestDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRequestDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleBasicResponseDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleResponseDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserBasicResponseDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserProfileResponseDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ErrorInfo.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Article.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCategory.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleState.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BadIdException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FileException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UnauthorizedException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleMapper.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JWTAuthenticationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JWTAuthorizationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomAuthenticationManager.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordEncoderConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SpringConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDetail.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FileUtil.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SwappApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂swapp
 ┃ ┃ ┃ ┃ ┗ 📂swapp
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SwappApplicationTests.java
 ```
The project follows a clean separation of concerns a 3-layer MVC architecture, client-server style, REST API type:

* **Model:** POJO classes utilizing Lombok annotations.
* **View:** A user-friendly Console/Terminal interface.
* **Controller:** The "brain" that orchestrates data flow between the View and the Repository.
* **Repository Pattern:** Used within the Model layer to abstract SQL queries and data persistence logic.

---

## ⚙️ Installation

Follow these steps to run the project locally:

1. **Clone the repository:**

    ```bash
    Clone the repository:
    git clone https://github.com/SwapFemCoders/SwApp_back.git
    
    Access the project:
    cd SwApp_back

    Install dependencies:
    npm install
    
    Run the project:
    mvn spring-boot:run
    
    The application will open at:
    http://localhost:8080



2. **Database Setup:** 
    Ensure you have a PostgreSQL instance running. Create your database and configure your credentials (URL, user, and password) inside the DBManager class to establish the connection.

    ```
    mvn clean install
    ```

3. **🔐 Security:**

The application uses token-based authentication (JWT) to protect user accounts.

This allows you to:

 * Log in securely.
 * Protect private paths.
 * Ensure that each user can only modify their own items.

4. **Run the application:**
```bash
 mvn exec:java -Dexec.mainClass="com.library.App"
 ```


## 📡 API REST

**Articles**

| Methods |	Endpoint |	Description |
|--------|-----------|--------------|
| GET | /api/articles | Obtener artículos |
| GET | /api/articles/{id} | Obtener artículo |
| POST | /api/articles | Crear artículo |

---

**Users**

| Methods |	Endpoint |	Description |
|--------|-----------|--------------|
| POST | /api/auth/register | Registro |
| POST | /api/auth/login | Login |

## 🗄 Database Schema
The database is designed with a relational structure, ensuring data integrity through normalization and foreign key constraints.

### 📊 Entity-Relationship Structure

* **Article**
* **ArticleCategoriy**
* **ArticleState**
* **ArticleStatus**
* **User**


<details>
<summary><b>Click to view SQL Table Definitions</b></summary>

```sql
-- TABLE 1: authors
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(300) NOT NULL
);

-- TABLE 2: books
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(17) UNIQUE,
    description VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TABLE 3: genres
CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- TABLE 4: books_authors
CREATE TABLE books_authors (
    book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
    author_id INTEGER REFERENCES authors(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, author_id)
);

-- TABLE 5: books_genres
CREATE TABLE books_genres (
    book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
    genre_id INTEGER REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, genre_id)
);
```
</details>

---

## 🧪 Testing
To ensure the reliability and stability of the library system, we have implemented a comprehensive testing suite focused on unit testing and component isolation.

### How to run the tests
You can execute the entire test suite directly from your terminal using Maven:

```bash
mvn test
```

If `mvn test` is not working in your terminal, ensure your `pom.xml` includes the **Maven Surefire Plugin** and the following dependencies:

1. **JUnit 5 (Jupiter)**: The core testing engine.
2. **Mockito Inline**: Required for mocking final classes and methods.
3. **Maven Surefire**: Necessary for the terminal to recognize tests during the build lifecycle.



## 👥 **Team**

**Cristina Viejó**<br>
GitHub: [https://github.com/krissvinti-ux](https://github.com/krissvinti-ux)<br>

**Ingrid López**<br>
GitHub: [https://github.com/Nuclea88](https://github.com/Nuclea88)<br>

**Manon Godfroy**<br>
GitHub: [https://github.com/ManonChab](https://github.com/ManonChab)<br>

**Geraldine Saco**<br>
GitHub: [https://github.com/GeraldineSaco](https://github.com/GeraldineSaco)<br>

**Sukaina Hadani**<br>
GitHub: [https://github.com/sukisu91-alt](hhttps://github.com/sukisu91-alt)<br>


## 📄 License

This FullStack project is developed for educational purposes.

---
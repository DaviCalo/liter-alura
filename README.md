# Gutendex Book Searcher

This project is a Java Spring Boot application that allows users to search for books using the Gutendex API, view registered books and authors, and filter them based on various criteria such as language and the authors' years of life.

## Features

  * **Search Books:** Search for books by title using the Gutendex API.
  * **List All Registered Books:** View all books that have been previously searched and saved in the application's database.
  * **List All Registered Authors:** See a list of all authors associated with the saved books.
  * **List Authors Alive in a Given Year:** Find authors who were alive during a specified year.
  * **List Books by Language:** Filter and display books based on their language.

## Technologies Used

  * **Java 17+**
  * **Spring Boot**
  * **Spring Data JPA**
  * **PostgreSQL** (or any other relational database configured)
  * **Maven** (for dependency management)
  * **Gutendex API**

## Getting Started

### Prerequisites

  * Java Development Kit (JDK) 17 or later
  * Maven
  * A PostgreSQL database (or your preferred relational database)

### Setup

1.  **Clone the repository:**

    ```bash
    git clone <repository_url>
    cd <project_directory>
    ```

2.  **Configure your database:**
    Open `src/main/resources/application.properties` (or `application.yml`) and configure your database connection settings. For example, for PostgreSQL:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3.  **Build the project:**

    ```bash
    mvn clean install
    ```

4.  **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    The application will start, and you can interact with it through the console menu.

## How to Use

Once the application is running, a menu will be displayed in your console:

```
1 - Buscar séries
2 - listar livros registrados
3 - listar autores registrados
4 - lsitar autores vivos em umm derterminado ano
5 - listar livros em um determinado idioma
0 - Sair
```

Enter the number corresponding to the action you wish to perform:

  * **1 - Buscar séries (Search Books):** Prompts you to enter the title of a book to search for. The application will fetch data from Gutendex and save it locally.
  * **2 - listar livros registrados (List Registered Books):** Displays all books that have been saved in your database, sorted by title.
  * **3 - listar autores registrados (List Registered Authors):** Shows all authors from the saved books, sorted by name.
  * **4 - lsitar autores vivos em umm derterminado ano (List Authors Alive in a Given Year):** Asks for a year and then lists authors who were alive during that year.
  * **5 - listar livros em um determinado idioma (List Books by Language):** Prompts you to enter a language and then displays books available in that language.
  * **0 - Sair (Exit):** Closes the application.
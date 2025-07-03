# EdwinPropierty – Student Academic Management API

This project is a backend REST API for managing students, courses, and academic records. It allows you to create, update, retrieve, and delete students and their academic histories, including course registrations and grades.

## 🧠 How It Works

- The API manages students, courses, and academic records using MongoDB as the database.
- It provides endpoints to:
  - Register and update students and their academic histories.
  - Add, update, or remove course records for each student.
  - Retrieve student information and their academic performance.

## 📂 Features

- RESTful endpoints for CRUD operations on students and academic records.
- Partial updates supported for academic registers.
- Returns appropriate HTTP status codes for all operations.
- Handles edge cases such as missing students or courses gracefully.

## ▶️ Usage

To run the project:

```bash
# Clone the repository
git clone <your-repo-url>
cd EdwinPropierty

# Install dependencies (if using Maven)
mvn install

# Run the Spring Boot application
mvn spring-boot:run
```

## ⚙️ Requirements

- Java 17 or higher
- Maven
- MongoDB

## 💾 Installation

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd EdwinPropierty
   ```
2. **Configure MongoDB connection** in `application.properties` if needed.
3. **Build and run the project:**
   ```bash
   mvn install
   mvn spring-boot:run
   ```

## 📄 Files

- `src/main/java/Lancelot/EdwinPropierty/controller/`: REST controllers for API endpoints.
- `src/main/java/Lancelot/EdwinPropierty/services/`: Business logic and service classes.
- `src/main/java/Lancelot/EdwinPropierty/model/`: Entity and DTO classes.
- `src/main/java/Lancelot/EdwinPropierty/repository/`: MongoDB repository interfaces.
- `README.md`: Project documentation.

---

Feel free to customize this README
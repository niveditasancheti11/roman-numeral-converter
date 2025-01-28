# Roman Numeral Converter Backend

A robust and scalable Spring Boot application designed to convert integers (1–3999) into Roman numerals. Built with clean architecture and modern Java development practices, this project serves as a reliable and high-performing REST API.

---

## Build and Run

### Prerequisites

- **Java 21**
- **Maven 3.8+**
- **Docker** (optional for containerized deployment)

### Steps

#### Build the application:

```bash
./mvnw clean install
```

#### Run the application:

```bash
./mvnw spring-boot:run
```

#### Access the application:

Open `http://localhost:8080` in your browser.

---

### Run with Docker

#### Build the Docker image:

```bash
docker build -t roman-numeral-converter .
```

#### Run the Docker container:

```bash
docker run -p 8080:8080 roman-numeral-converter
```

---

## Engineering and Testing Methodology

### Engineering Principles

- **Clean Architecture**: Adheres to the separation of concerns by isolating service logic, controller, and repository layers.
- **Error Handling**: Implements a global exception handler to provide user-friendly error messages.
- **Caching**: Leverages `@Cacheable` to reduce redundant database calls and enhance performance.
- **Modular Design**: Organizes code using a package-by-feature structure for better maintainability.

### Testing Strategy

- **Unit Tests**:

  - Written for core services like `RomanNumeralService` and repository classes.
  - Ensures correctness of conversion logic and caching mechanisms.

  ```bash
  ./mvnw test
  ```

- **Integration Tests**:

  - Validates API endpoints and database interactions.
  - Ensures proper error handling for invalid inputs.

- **Manual Testing**:

  - Validates functionality for both valid and invalid inputs.
  - Tests API integration using tools like Postman.

---

## Packaging Layout

```plaintext
src/
├── main/java/com/adobe/roman_numeral_converter
│   ├── config/                  # Configuration classes (e.g., CORS, exception handling)
│   │   ├── CorsConfig.java
│   │   ├── GlobalExceptionHandler.java
│   ├── controller/              # REST controllers
│   │   └── RomanNumeralController.java
│   ├── entity/                  # JPA entities
│   │   └── RomanNumeralEntity.java
│   ├── repository/              # JPA repositories
│   │   └── RomanNumeralRepository.java
│   ├── service/                 # Business logic
│   │   └── RomanNumeralService.java
│   └── RomanNumeralConverterApplication.java
├── test/                        # Unit and integration tests
└── resources/
    ├── application.properties   # Spring configuration
    └── data.sql                 # Initial test data
```

---

## Dependency Attribution

- **Spring Boot**: Provides a framework for creating production-ready applications.
- **Spring Data JPA**: Simplifies database interactions.
- **Lombok**: Reduces boilerplate code for getters, setters, etc.
- **H2 Database**: Lightweight in-memory database for testing.
- **Spring Cache**: Enhances performance with caching annotations.
- **Maven Wrapper**: Simplifies build process for consistent environments.

---

## Key Components

### Roman Numeral Conversion Logic

Efficient conversion logic using `TreeMap`:

```java
public String toRoman(int number) {
    if (number < 1 || number > 3999) {
        throw new IllegalArgumentException("Number out of range (1-3999)");
    }
    int l = map.floorKey(number);
    if (number == l)
        return map.get(number);
    return map.get(l) + toRoman(number - l);
}
```

### Persistent Storage with Caching

Combines JPA and caching for optimal performance:

```java
@Cacheable("romanNumerals")
public RomanNumeralEntity getOrSaveRomanNumeral(int number) {
    return repository.findById(number).orElseGet(() -> {
        String roman = toRoman(number);
        RomanNumeralEntity entity = new RomanNumeralEntity(number, roman);
        repository.save(entity);
        return entity;
    });
}
```

---

## Future Enhancements

- Add functionality for converting Roman numerals back to integers.
- Deploy the application using CI/CD pipelines and Google Cloud Run.
- Implement authentication and rate-limiting for secure and controlled access.

---

## References

- [Roman Numeral Specification](https://en.wikipedia.org/wiki/Roman_numerals)

---

## Author

**Nivedita Sancheti**\
LinkedIn: [Profile](https://www.linkedin.com/in/niveditasancheti11)\
GitHub: [niveditasancheti11](https://github.com/niveditasancheti11)


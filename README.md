# Temperature Converter

This is an individual assignment for the Software Development course. The project is a Java application developed with JUnit testing, JaCoCo code coverage, Jenkins, Docker, JavaFX, and MariaDB. It also includes localization.

The application does not have a specific purpose or concept. It includes temperature conversion and time calculation from speed and distance.

## Technologies and Tools Used

| Area | Technology |
| --- | --- |
| Language | Java 17 |
| Build tool | Maven |
| Unit testing | JUnit Jupiter 5.11.4 |
| Code coverage | JaCoCo 0.8.15 |
| Localization | Java `ResourceBundle` and UTF-8 `.properties` files |
| CI/CD | Jenkins |
| Containerization | Docker |

## Design Approach & Implementation Method

The project uses an MVC structure.

| Part | Implementation |
| --- | --- |
| Model | Stores temperature, time, and conversion data. |
| View | Shows input fields, buttons, and tables. |
| Controller | Gets input from the view and calls the services. |
| Service | Converts temperatures and calculates time. |
| DAO | Saves and loads data from the database. |
| Database | Saves temperature and time records. |

The JavaFX application has two sections. Both save their records in the database. The database tables are in [database.sql](TODO).

- **Temperature Records**: choose a conversion type, enter a temperature, convert it, and save the result. The result is rounded to two decimal places.
- **Time Records**: enter speed and distance, calculate time, and save the result.

## Testing & Quality Assurance Steps

The project uses automated JUnit tests.

| Test area | Test cases |
| --- | --- |
| Temperature conversion | Fahrenheit to Celsius, Celsius to Fahrenheit, and Kelvin to Celsius |
| Extreme temperatures | Values below -40 °C and above 50 °C |
| Models | Temperature, time, and conversion data |
| Services and controllers | Adding and reading temperature and time records |
| DAO classes | Saving and loading test records |

Run the tests:

```bash
mvn test
```

The project also has a [JaCoCo code coverage report](TODO). Jenkins builds the project, runs tests, creates the report, and builds the Docker image.

## How to Run

### Prerequisites

Install:

- JDK 17
- Maven
- MariaDB for local use
- Docker Desktop for Docker setup

### Run locally

1. Clone the repository.

   ```bash
   git clone TODO
   cd TODO
   ```

2. Create a MariaDB database named inclass and run [database.sql](TODO).

3. Use these database settings if needed.

   ```text
   TODO
   ```

4. Run tests and start the application.

   ```bash
   mvn test
   mvn javafx:run
   ```

### Run with Docker

1. Start Docker Desktop.

2. Run:

   ```bash
   docker compose up --build
   ```

3. Stop the containers when done.

   ```bash
   docker compose down
   ```

On macOS, start XQuartz before running the Docker GUI. On Windows, start Xming.

## Author

Kseniia Shlenskaia  
Tieto-ja viestintätekniikka  
TVT25K-O  
Ohjelmistotuotantoprojekti 1 TX00EY27-3012

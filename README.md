# Software Engineering Project 2

This is an individual assignment for the Software Engineering Project course. The project is a JavaFX application with temperature conversion, time calculation, MariaDB record storage, and localization.

The repository also contains Maven, JUnit, JaCoCo, Jenkins, Docker, and Docker Compose configuration.

## Technologies and Tools Used

| Area | Technology |
| --- | --- |
| Language | Java 17 |
| Build tool | Maven |
| GUI | JavaFX |
| Unit testing | JUnit Jupiter 5.11.4 |
| Code coverage | JaCoCo 0.8.15 |
| Localization | Java `ResourceBundle` and UTF-8 `.properties` files for English, Finnish, Swedish, Japanese, and Arabic |
| Database | MariaDB |
| Database access | MariaDB JDBC Driver |
| CI/CD | Jenkins |
| Containerization | Docker |
| Local multi-container setup | Docker Compose |

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

The JavaFX application has two sections. Both save their records in the database. The database tables are defined in [database.sql](database.sql).

- **Temperature Records**: choose a conversion type, enter a temperature, convert it, and save the result.
- **Time Records**: enter speed and distance, calculate time, and save the result.

## Database Localization

The application supports English, Finnish, Swedish, Japanese, and Arabic. For texts in the database, it uses field localization. With this approach each translated text has one column per language (e.g. `name_en`, `name_fi`, `description_en`, `description_fi`). The application chooses the column that matches the selected language. As a default language, it uses English.

**Schema** (see [database.sql](database.sql)):

| Table | Columns |
| --- | --- |
| units | id, unit, sign_[language code], name_[language code] |
| temperature_types | id, type_key, from_unit_id, to_unit_id, name_[language code], descr_[language code] |
| time_record_types | id, field_key, unit_id, name_[language code], descr_[language code] |
| temperature_records | id, temperature_type_id, from_temperature, to_temperature, lang_code, created_at |
| time_records | id, speed, distance, time, created_at |

**Advantages of Field Localization**
- The application has only five languages and the list is not going to expand.
- The application has very little translated data.
- The application does not need to use complex SQL queries with joins to get a translation.

The current approach may have disadvantage in case of further development of the project. Currently, each new language needs new columns. This would be a problem if the list of used languages grows, but it is appropriate for this project at the current stage of development.

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

The project also has a [JaCoCo code coverage report](https://x-bananer.github.io/software-engineering-project-2/). To create it locally, run `mvn verify` and open `target/site/jacoco/index.html`. Jenkins builds the project, runs tests, creates the report, and builds the Docker image.

## How to Run

### Prerequisites

Install:

- JDK 17
- Maven
- MariaDB for local use
- Docker Desktop for the Docker configuration

### Run locally

1. Start MariaDB, then create a database named `sep_2` and run [database.sql](database.sql). The script resets
   the application tables and inserts the localized lookup data.

   ```bash
   mariadb -uroot -p -e "CREATE DATABASE IF NOT EXISTS sep_2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci"
   mariadb -uroot -p sep_2 < database.sql
   ```

2. Check the database connection settings. By default, the application connects to MariaDB on `localhost:3306`, database `sep_2`, as user `root` with password `1234`. If your setup is different, set these environment variables before running the tests or the application:

   | Variable | Default |
   | --- | --- |
   | `DB_HOST` | `localhost` |
   | `DB_PORT` | `3306` |
   | `DB_NAME` | `sep_2` |
   | `DB_USER` | `root` |
   | `DB_PASSWORD` | `1234` |

   For example: `export DB_PASSWORD=my-password`.

3. Run tests and start the application. MariaDB must be running, and the computer needs a desktop environment to show the JavaFX window.

   ```bash
   mvn test
   mvn javafx:run
   ```

### Run with Docker

1. Start Docker Desktop.

2. On macOS, start XQuartz. On Windows, start Xming. This is required to display the JavaFX GUI from the container.

3. From the project root, run:

   ```bash
   docker compose up --build
   ```

   Docker Compose starts the MariaDB database and the application. The database schema is initialized from [database.sql](database.sql).

4. Stop the containers when finished:

   ```bash
   docker compose down
   ```

## Author

Kseniia Shlenskaia  
Tieto-ja viestintätekniikka  
TVT25K-O  
Ohjelmistotuotantoprojekti 1 TX00EY27-3012

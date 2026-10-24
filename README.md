# Temperature Converter

Console application for converting temperatures. It was created for an in-class software engineering assignment focused on Java, testing, code coverage, localization, Docker, and CI/CD.

## Features

- Converts Fahrenheit to Celsius.
- Converts Celsius to Fahrenheit.
- Marks a Celsius temperature as extreme when it is below `-40 °C` or above `50 °C`.
- Lets the user choose English, Finnish, Swedish, or Japanese at startup.
- Runs in a loop until the user selects `0`.

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

## Testing and Code Coverage

`TemperatureConverterTest` tests multiple input values for both conversion methods and boundary cases for extreme temperatures: `-40`, `50`, `-41`, and `51`.

Run tests:

```bash
mvn test
```

Generate the JaCoCo report:

```bash
mvn verify
```

Open the local report at `target/site/jacoco/index.html`.

The Jenkins pipeline builds the project, runs the tests, publishes the JaCoCo report and JUnit results in Jenkins, then builds and pushes the Docker image to Docker Hub.

## How to Run Locally

### Prerequisites

- JDK 17
- Maven

### Run the application

```bash
git clone https://github.com/x-bananer/software-engineering-project-2.git
cd software-engineering-project-2
mvn clean package
java -jar target/sep-1.0.0.jar
```

At startup, select a language:

```text
0 - English, 1 - Suomi, 2 - Svenska, 3 - 日本語
```

Then select a conversion type and enter a temperature. Enter `0` in the conversion menu to exit.

## Run with Docker

### Prerequisites

- Docker Desktop

Build the image:

```bash
docker build -t temperature-converter .
```

Run the application interactively:

```bash
docker run -it --rm temperature-converter
```

The published Docker image is available as `ksenishl/software-engineering-project-2:latest`.

## Author

Kseniia Shlenskaia  
Tieto- ja viestintätekniikka  
TVT25K-O  
Ohjelmistotuotantoprojekti 2

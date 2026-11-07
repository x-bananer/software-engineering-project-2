DROP TABLE time_records;
DROP TABLE temperature_records;
DROP TABLE temperature_types;

CREATE TABLE temperature_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type_key VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO temperature_types (type_key) VALUES
    ('fahrenheitToCelsius'),
    ('celsiusToFahrenheit'),
    ('kelvinToCelsius');

CREATE TABLE temperature_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    temperature_type_id INT NOT NULL,
    from_temperature DECIMAL(10,2) NOT NULL,
    to_temperature DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (temperature_type_id) REFERENCES temperature_types(id)
);

CREATE TABLE time_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speed DECIMAL(10,2) NOT NULL,
    distance DECIMAL(10,2) NOT NULL,
    time DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

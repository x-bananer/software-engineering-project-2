CREATE TABLE IF NOT EXISTS temperature_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT IGNORE INTO temperature_types (type_name) VALUES
    ('Fahrenheit to Celsius'),
    ('Celsius to Fahrenheit'),
    ('Kelvin to Celsius');

CREATE TABLE IF NOT EXISTS temperature_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    temperature_type_id INT NOT NULL,
    from_temperature DECIMAL(10,2) NOT NULL,
    to_temperature DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (temperature_type_id) REFERENCES temperature_types(id)
);

CREATE TABLE IF NOT EXISTS time_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speed DECIMAL(10,2) NOT NULL,
    distance DECIMAL(10,2) NOT NULL,
    time DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS time_records;
DROP TABLE IF EXISTS temperature_records;
DROP TABLE IF EXISTS time_record_types;
DROP TABLE IF EXISTS temperature_types;
DROP TABLE IF EXISTS units;

CREATE TABLE units (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unit VARCHAR(50) NOT NULL UNIQUE,
    sign_en VARCHAR(20) NOT NULL,
    sign_fi VARCHAR(20) NOT NULL,
    sign_sv VARCHAR(20) NOT NULL,
    sign_ja VARCHAR(20) NOT NULL,
    sign_ar VARCHAR(20) NOT NULL,
    name_en VARCHAR(100) NOT NULL,
    name_fi VARCHAR(100) NOT NULL,
    name_sv VARCHAR(100) NOT NULL,
    name_ja VARCHAR(100) NOT NULL,
    name_ar VARCHAR(100) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE temperature_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type_key VARCHAR(50) NOT NULL UNIQUE,
    from_unit_id INT NOT NULL,
    to_unit_id INT NOT NULL,
    name_en VARCHAR(100) NOT NULL,
    name_fi VARCHAR(100) NOT NULL,
    name_sv VARCHAR(100) NOT NULL,
    name_ja VARCHAR(100) NOT NULL,
    name_ar VARCHAR(100) NOT NULL,
    descr_en VARCHAR(255) NOT NULL,
    descr_fi VARCHAR(255) NOT NULL,
    descr_sv VARCHAR(255) NOT NULL,
    descr_ja VARCHAR(255) NOT NULL,
    descr_ar VARCHAR(255) NOT NULL,
    FOREIGN KEY (from_unit_id) REFERENCES units(id),
    FOREIGN KEY (to_unit_id) REFERENCES units(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE time_record_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    field_key VARCHAR(50) NOT NULL UNIQUE,
    unit_id INT NOT NULL,
    name_en VARCHAR(100) NOT NULL,
    name_fi VARCHAR(100) NOT NULL,
    name_sv VARCHAR(100) NOT NULL,
    name_ja VARCHAR(100) NOT NULL,
    name_ar VARCHAR(100) NOT NULL,
    descr_en VARCHAR(255) NOT NULL,
    descr_fi VARCHAR(255) NOT NULL,
    descr_sv VARCHAR(255) NOT NULL,
    descr_ja VARCHAR(255) NOT NULL,
    descr_ar VARCHAR(255) NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES units(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE temperature_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    temperature_type_id INT NOT NULL,
    from_temperature DECIMAL(10, 2) NOT NULL,
    to_temperature DECIMAL(10, 2) NOT NULL,
    lang_code VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (temperature_type_id) REFERENCES temperature_types(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE time_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speed DECIMAL(10, 2) NOT NULL,
    distance DECIMAL(10, 2) NOT NULL,
    time DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO units (
    unit, sign_en, sign_fi, sign_sv, sign_ja, sign_ar,
    name_en, name_fi, name_sv, name_ja, name_ar
) VALUES
    ('celsius', '°C', '°C', '°C', '℃', '°م', 'Celsius', 'Celsius', 'Celsius', '摂氏', 'مئوية'),
    ('fahrenheit', '°F', '°F', '°F', '°F', '°ف', 'Fahrenheit', 'Fahrenheit', 'Fahrenheit', '華氏', 'فهرنهايت'),
    ('kelvin', 'K', 'K', 'K', 'K', 'ك', 'Kelvin', 'Kelvin', 'Kelvin', 'ケルビン', 'كلفن'),
    ('kilometres_per_hour', 'km/h', 'km/h', 'km/h', 'km/h', 'كم/س', 'Kilometres per hour', 'Kilometriä tunnissa', 'Kilometer per timme', 'キロメートル毎時', 'كيلومتر في الساعة'),
    ('kilometres', 'km', 'km', 'km', 'km', 'كم', 'Kilometres', 'Kilometriä', 'Kilometer', 'キロメートル', 'كيلومتر'),
    ('hours', 'h', 'h', 'h', 'h', 'س', 'Hours', 'Tuntia', 'Timmar', '時間', 'ساعة');

INSERT INTO temperature_types (
    type_key, from_unit_id, to_unit_id,
    name_en, name_fi, name_sv, name_ja, name_ar,
    descr_en, descr_fi, descr_sv, descr_ja, descr_ar
) VALUES
    ('fahrenheitToCelsius', 2, 1,
        'Fahrenheit to Celsius', 'Fahrenheit Celsius-asteiksi', 'Fahrenheit till Celsius', '華氏から摂氏へ', 'فهرنهايت إلى مئوية',
        'Convert Fahrenheit to Celsius', 'Muunna Fahrenheit Celsius-asteiksi', 'Omvandla Fahrenheit till Celsius', '華氏を摂氏に変換', 'حوّل فهرنهايت إلى مئوية'),
    ('celsiusToFahrenheit', 1, 2,
        'Celsius to Fahrenheit', 'Celsius Fahrenheit-asteiksi', 'Celsius till Fahrenheit', '摂氏から華氏へ', 'مئوية إلى فهرنهايت',
        'Convert Celsius to Fahrenheit', 'Muunna Celsius Fahrenheit-asteiksi', 'Omvandla Celsius till Fahrenheit', '摂氏を華氏に変換', 'حوّل مئوية إلى فهرنهايت'),
    ('kelvinToCelsius', 3, 1,
        'Kelvin to Celsius', 'Kelvin Celsius-asteiksi', 'Kelvin till Celsius', 'ケルビンから摂氏へ', 'كلفن إلى مئوية',
        'Convert Kelvin to Celsius', 'Muunna Kelvin Celsius-asteiksi', 'Omvandla Kelvin till Celsius', 'ケルビンを摂氏に変換', 'حوّل كلفن إلى مئوية');

INSERT INTO time_record_types (
    field_key, unit_id,
    name_en, name_fi, name_sv, name_ja, name_ar,
    descr_en, descr_fi, descr_sv, descr_ja, descr_ar
) VALUES
    ('speed', 4,
        'Speed', 'Nopeus', 'Hastighet', '速度', 'السرعة',
        'How fast you move', 'Kuinka nopeasti liikut', 'Hur snabbt du rör dig', 'あなたがどれだけ速く移動しているか', 'مدى سرعة حركتك'),
    ('distance', 5,
        'Distance', 'Matka', 'Avstånd', '距離', 'المسافة',
        'How far you travel', 'Kuinka pitkän matkan kuljet', 'Hur långt du färdas', 'あなたがどれだけ遠くまで移動するか', 'المسافة التي تقطعها'),
    ('time', 6,
        'Time', 'Aika', 'Tid', '時間', 'الوقت',
        'How long the journey takes', 'Kuinka kauan matka kestää', 'Hur länge resan tar', '移動にかかる時間', 'المدة التي تستغرقها الرحلة');

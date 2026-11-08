package com.sep.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureRecordTest {
	@Test
	void storesFahrenheitToCelsiusRecord() {
		TemperatureType type = new TemperatureType(1, "fahrenheitToCelsius", "Fahrenheit to Celsius", "Converts Fahrenheit to Celsius.", "°F", "°C");
		TemperatureRecord record = new TemperatureRecord(type, 32.0, 0.0);

		assertEquals("32.0 °F", record.getFrom());
		assertEquals("0.0 °C", record.getTo());
	}

	@Test
	void storesCelsiusToFahrenheitRecord() {
		TemperatureType type = new TemperatureType(2, "celsiusToFahrenheit", "Celsius to Fahrenheit", "Converts Celsius to Fahrenheit.", "°C", "°F");
		TemperatureRecord record = new TemperatureRecord(type, 0.0, 32.0);

		assertEquals("0.0 °C", record.getFrom());
		assertEquals("32.0 °F", record.getTo());
	}

	@Test
	void storesKelvinToCelsiusRecord() {
		TemperatureType type = new TemperatureType(3, "kelvinToCelsius", "Kelvin to Celsius", "Converts Kelvin to Celsius.", "K", "°C");
		TemperatureRecord record = new TemperatureRecord(type, 273.15, 0.0);

		assertEquals("273.15 K", record.getFrom());
		assertEquals("0.0 °C", record.getTo());
	}
}

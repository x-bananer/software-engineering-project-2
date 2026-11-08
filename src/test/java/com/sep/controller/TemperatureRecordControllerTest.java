package com.sep.controller;

import com.sep.model.TemperatureType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureRecordControllerTest {
	private final TemperatureRecordController controller = new TemperatureRecordController();

	@Test
	void rejectsInvalidNumber() {
		TemperatureType type = new TemperatureType(1, "celsiusToFahrenheit", "", "", "", "");

		assertThrows(NumberFormatException.class, () -> controller.add(type, "abc", "en"));
	}
}

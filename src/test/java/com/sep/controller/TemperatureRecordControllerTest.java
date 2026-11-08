package com.sep.controller;

import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureRecordControllerTest {
	private final TemperatureRecordController controller = new TemperatureRecordController();

	@Test
	void rejectsInvalidNumber() {
		TemperatureType type = new TemperatureType(1, "celsiusToFahrenheit", "", "", "", "");

		assertThrows(NumberFormatException.class, () -> controller.add(type, "abc", "en"));
	}

	@Test
	@Tag("db")
	void loadsTypes() throws SQLException {
		assertEquals(3, controller.getTypes("ja").size());
	}

	@Test
	@Tag("db")
	void addsRecordFromText() throws SQLException {
		TemperatureType type = controller.getTypes("en").get(1);

		TemperatureRecord record = controller.add(type, "0", "en");

		assertEquals(0, record.getFromTemp());
		assertEquals(32, record.getToTemp(), 0.001);
	}

	@Test
	@Tag("db")
	void returnsRecords() throws SQLException {
		TemperatureType type = controller.getTypes("en").get(0);
		controller.add(type, "1", "en");

		assertFalse(controller.getAll("en").isEmpty());
	}
}

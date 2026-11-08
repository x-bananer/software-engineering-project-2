package com.sep.service;

import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureRecordServiceTest {
	private final TemperatureRecordService service = new TemperatureRecordService();

	@Test
	void loadsTemperatureTypes() throws SQLException {
		List<TemperatureType> types = service.getTypes("en");

		assertEquals(3, types.size());
	}

	@Test
	void addsFahrenheitToCelsiusRecord() throws SQLException {
		TemperatureType type = service.getTypes("en").get(0);

		TemperatureRecord record = service.add(type, 212, "en");

		assertEquals(212, record.getFromTemp());
		assertEquals(100, record.getToTemp(), 0.001);
	}

	@Test
	void addsCelsiusToFahrenheitRecord() throws SQLException {
		TemperatureType type = service.getTypes("en").get(1);

		TemperatureRecord record = service.add(type, 100, "en");

		assertEquals(212, record.getToTemp(), 0.001);
	}

	@Test
	void addsKelvinToCelsiusRecord() throws SQLException {
		TemperatureType type = service.getTypes("en").get(2);

		TemperatureRecord record = service.add(type, 273.15, "en");

		assertEquals(0, record.getToTemp(), 0.001);
	}

	@Test
	void rejectsUnknownTemperatureType() {
		TemperatureType type = new TemperatureType(0, "unknown", "", "", "", "");

		IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> service.add(type, 1, "en"));

		assertEquals("unknownTemperatureType", error.getMessage());
	}

	@Test
	void returnsSavedRecords() throws SQLException {
		TemperatureType type = service.getTypes("en").get(1);
		service.add(type, 37, "en");

		List<TemperatureRecord> records = service.getAll("en");

		assertFalse(records.isEmpty());
		assertEquals(37, records.get(0).getFromTemp());
	}
}

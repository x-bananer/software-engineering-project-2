package com.sep.service;

import com.sep.model.TemperatureType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureRecordServiceTest {
	private final TemperatureRecordService service = new TemperatureRecordService();

	@Test
	void rejectsUnknownTemperatureType() {
		TemperatureType type = new TemperatureType(0, "unknown", "", "", "", "");

		IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> service.add(type, 1, "en"));

		assertEquals("unknownTemperatureType", error.getMessage());
	}
}

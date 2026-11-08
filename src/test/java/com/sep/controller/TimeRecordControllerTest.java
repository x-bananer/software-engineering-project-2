package com.sep.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeRecordControllerTest {
	private final TimeRecordController controller = new TimeRecordController();

	@Test
	void rejectsInvalidSpeed() {
		assertThrows(NumberFormatException.class, () -> controller.add("fast", "100"));
	}

	@Test
	void rejectsInvalidDistance() {
		assertThrows(NumberFormatException.class, () -> controller.add("50", ""));
	}
}

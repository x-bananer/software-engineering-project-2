package com.sep.controller;

import com.sep.model.TimeRecord;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeRecordControllerTest {
	private final TimeRecordController controller = new TimeRecordController();

	@Test
	void parsesInputAndAddsRecord() throws SQLException {
		TimeRecord record = controller.add("50", "100");

		assertEquals(50, record.getSpeed());
		assertEquals(100, record.getDistance());
		assertEquals(2, record.getTime());
	}

	@Test
	void rejectsInvalidSpeed() {
		assertThrows(NumberFormatException.class, () -> controller.add("fast", "100"));
	}

	@Test
	void rejectsInvalidDistance() {
		assertThrows(NumberFormatException.class, () -> controller.add("50", ""));
	}

	@Test
	void returnsRecords() throws SQLException {
		controller.add("20", "10");

		assertFalse(controller.getAll().isEmpty());
	}

	@Test
	void loadsTypes() throws SQLException {
		assertEquals(3, controller.getTypes("fi").size());
	}
}

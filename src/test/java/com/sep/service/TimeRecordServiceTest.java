package com.sep.service;

import com.sep.model.TimeRecord;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TimeRecordServiceTest {
	private final TimeRecordService service = new TimeRecordService();

	@Test
	void calculatesTimeFromDistanceAndSpeed() {
		assertEquals(2.0, service.calculateTime(50.0, 100.0));
		assertEquals(0.5, service.calculateTime(80.0, 40.0));
	}

	@Test
	void addsRecordWithCalculatedTime() throws SQLException {
		TimeRecord record = service.add(60, 120);

		assertEquals(60, record.getSpeed());
		assertEquals(120, record.getDistance());
		assertEquals(2, record.getTime());
	}

	@Test
	void returnsSavedRecords() throws SQLException {
		service.add(10, 5);

		List<TimeRecord> records = service.getAll();

		assertFalse(records.isEmpty());
		assertEquals(0.5, records.get(records.size() - 1).getTime());
	}

	@Test
	void loadsTimeRecordTypes() throws SQLException {
		assertEquals(3, service.getTypes("en").size());
	}
}

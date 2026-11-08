package com.sep.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeRecordTest {
	@Test
	void storesTimeRecordValues() {
		TimeRecord record = new TimeRecord(80.0, 40.0, 0.5);

		assertEquals(80.0, record.getSpeed());
		assertEquals(40.0, record.getDistance());
		assertEquals(0.5, record.getTime());
	}
}

package com.sep.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeRecordTest {
	@Test
	void storesTimeRecordValues() {
		TimeRecord record = new TimeRecord(80.0, 40.0, 0.5);

		assertEquals(80.0, record.getSpeedValue());
		assertEquals(40.0, record.getDistanceValue());
		assertEquals(0.5, record.getTimeValue());
		assertEquals("80.0 km/h", record.getSpeed());
		assertEquals("40.0 km", record.getDistance());
		assertEquals("0.5 h", record.getTime());
	}
}

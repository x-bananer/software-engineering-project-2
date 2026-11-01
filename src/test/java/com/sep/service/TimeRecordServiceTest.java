package com.sep.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeRecordServiceTest {
	private final TimeRecordService service = new TimeRecordService();

	@Test
	void calculatesTimeFromDistanceAndSpeed() {
		assertEquals(2.0, service.calculateTime(50.0, 100.0));
		assertEquals(0.5, service.calculateTime(80.0, 40.0));
	}
}

package com.sep.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTypeTest {
	@Test
	void storesIdAndKey() {
		TemperatureType type = new TemperatureType(1, "fahrenheitToCelsius");

		assertEquals(1, type.getId());
		assertEquals("fahrenheitToCelsius", type.getKey());
		assertEquals("fahrenheitToCelsius", type.toString());
	}
}

package com.sep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureConverterTest {
    private final TemperatureConverter converter = new TemperatureConverter();

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(5.0, converter.celsiusToFahrenheit(-15.0));
        assertEquals(212.0, converter.celsiusToFahrenheit(100.0));
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40.0));
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0));
        assertEquals(5.0, converter.fahrenheitToCelsius(41.0));
        assertEquals(-40.0, converter.fahrenheitToCelsius(-40.0));
    }

    @Test
    void testIsExtremeTemperature() {
        assertFalse(converter.isExtremeTemperature(-40.0));
        assertFalse(converter.isExtremeTemperature(50.0));
        assertTrue(converter.isExtremeTemperature(-41.0));
        assertTrue(converter.isExtremeTemperature(51.0));
		assertFalse(converter.isExtremeTemperature(0.0));
    }
}

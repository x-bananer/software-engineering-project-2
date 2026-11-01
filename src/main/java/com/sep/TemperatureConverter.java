package com.sep;

public class TemperatureConverter {
	public double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9;
	}

	public double celsiusToFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
	}

	public boolean isExtremeTemperature(double celsius) {
		return celsius < -40.0 || celsius > 50.0;
	}
}

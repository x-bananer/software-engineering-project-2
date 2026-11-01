package com.sep.service;

import com.sep.TemperatureConverter;
import com.sep.dao.TemperatureRecordDAO;
import com.sep.dao.TemperatureTypeDAO;
import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;

import java.sql.SQLException;
import java.util.List;

public class TemperatureRecordService {
	private final TemperatureConverter converter = new TemperatureConverter();
	private final TemperatureRecordDAO dao = new TemperatureRecordDAO();
	private final TemperatureTypeDAO typeDAO = new TemperatureTypeDAO();

	public TemperatureRecord add(TemperatureType type, double value) throws SQLException {
		double result = switch (type.getName()) {
			case "Fahrenheit to Celsius" -> converter.fahrenheitToCelsius(value);
			case "Celsius to Fahrenheit" -> converter.celsiusToFahrenheit(value);
			case "Kelvin to Celsius" -> converter.kelvinToCelsius(value);
			default -> throw new IllegalArgumentException("Unknown temperature type");
		};

		TemperatureRecord record = new TemperatureRecord(type, value, result);

		dao.add(record);
		
		return record;
	}

	public List<TemperatureRecord> getAll() throws SQLException {
		return dao.getAll();
	}

	public List<TemperatureType> getTypes() throws SQLException {
		return typeDAO.getAll();
	}
}

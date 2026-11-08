package com.sep.dao;

import com.sep.db.DatabaseConnection;
import com.sep.model.TemperatureRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRecordDAO {

	public void add(TemperatureRecord record, String langCode) throws SQLException {
		String sql = "INSERT INTO temperature_records (temperature_type_id, from_temperature, to_temperature, lang_code) VALUES (?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, record.getTempType().getId());
			statement.setDouble(2, record.getFromTemp());
			statement.setDouble(3, record.getToTemp());
			statement.setString(4, langCode);

			statement.executeUpdate();
		}
	}

	public List<TemperatureRecord> getAll(String langCode) throws SQLException {
		List<TemperatureRecord> records = new ArrayList<>();
		TemperatureTypeDAO typeDAO = new TemperatureTypeDAO();
		
		String sql = "SELECT temperature_type_id, from_temperature, to_temperature FROM temperature_records ORDER BY id DESC LIMIT 5";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				records.add(new TemperatureRecord(
					typeDAO.getById(result.getInt("temperature_type_id"), langCode),
					result.getDouble("from_temperature"),
					result.getDouble("to_temperature")));
			}
		}

		return records;
	}

}

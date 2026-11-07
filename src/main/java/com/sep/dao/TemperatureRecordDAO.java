package com.sep.dao;

import com.sep.db.DatabaseConnection;
import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRecordDAO {

	public void add(TemperatureRecord record) throws SQLException {
		String sql = "INSERT INTO temperature_records (temperature_type_id, from_temperature, to_temperature) VALUES (?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, record.getTemperatureType().getId());
			statement.setDouble(2, record.getFromTemperature());
			statement.setDouble(3, record.getToTemperature());

			statement.executeUpdate();
		}
	}

	public List<TemperatureRecord> getAll() throws SQLException {
		List<TemperatureRecord> records = new ArrayList<>();
		String sql = "SELECT r.from_temperature, r.to_temperature, t.id, t.type_key, t.name, t.from_sign, t.to_sign "
				+ "FROM temperature_records r JOIN temperature_types t ON r.temperature_type_id = t.id ORDER BY r.id";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				records.add(new TemperatureRecord(
						new TemperatureType(
								result.getInt("id"),
								result.getString("type_key"),
								result.getString("name"),
								result.getString("from_sign"),
								result.getString("to_sign")),
						result.getDouble("from_temperature"),
						result.getDouble("to_temperature")));
			}
		}

		return records;
	}
}

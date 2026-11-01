package com.sep.dao;

import com.sep.db.DatabaseConnection;
import com.sep.model.TimeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeRecordDAO {

	public void add(TimeRecord record) throws SQLException {
		String sql = "INSERT INTO time_records (speed, distance, `time`) VALUES (?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDouble(1, record.getSpeedValue());
			statement.setDouble(2, record.getDistanceValue());
			statement.setDouble(3, record.getTimeValue());

			statement.executeUpdate();
		}
	}

	public List<TimeRecord> getAll() throws SQLException {
		List<TimeRecord> records = new ArrayList<>();
		String sql = "SELECT speed, distance, `time` FROM time_records ORDER BY id";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				records.add(new TimeRecord(
						result.getDouble("speed"),
						result.getDouble("distance"),
						result.getDouble("time")));
			}
		}

		return records;
	}
}

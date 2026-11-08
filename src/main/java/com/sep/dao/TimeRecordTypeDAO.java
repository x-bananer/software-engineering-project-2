package com.sep.dao;

import com.sep.db.DatabaseConnection;
import com.sep.model.TimeRecordType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeRecordTypeDAO {
	public List<TimeRecordType> getAll(String langCode) throws SQLException {
		List<TimeRecordType> types = new ArrayList<>();
		String lang = TemperatureTypeDAO.getLang(langCode);
		String sql = "SELECT name_" + lang + ", descr_" + lang + ", unit_id FROM time_record_types ORDER BY id";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					types.add(new TimeRecordType(
						result.getString("name_" + lang),
						getSign(result.getInt("unit_id"), lang),
						result.getString("descr_" + lang)));
				}
			}
		}

		return types;
	}

	private String getSign(int unitId, String lang) throws SQLException {
		String sql = "SELECT sign_" + lang + " FROM units WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, unitId);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				return result.getString("sign_" + lang);
			}
		}
	}
}
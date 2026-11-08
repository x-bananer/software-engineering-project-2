package com.sep.dao;

import com.sep.db.DatabaseConnection;
import com.sep.model.TemperatureType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemperatureTypeDAO {

	public List<TemperatureType> getAll(String langCode) throws SQLException {
		List<TemperatureType> types = new ArrayList<>();
		String lang = getLang(langCode);
		String sql = "SELECT id, type_key, name_" + lang + ", descr_" + lang + ", from_unit_id, to_unit_id FROM temperature_types ORDER BY id";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					types.add(new TemperatureType(
						result.getInt("id"),
						result.getString("type_key"),
						result.getString("name_" + lang),
						result.getString("descr_" + lang),
						getSign(result.getInt("from_unit_id"), lang),
						getSign(result.getInt("to_unit_id"), lang)));
				}
			}
		}
		return types;
	}

	public TemperatureType getById(int id, String langCode) throws SQLException {
		String lang = getLang(langCode);
		String sql = "SELECT id, type_key, name_" + lang + ", descr_" + lang + ", from_unit_id, to_unit_id FROM temperature_types WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				return new TemperatureType(
					result.getInt("id"),
					result.getString("type_key"),
					result.getString("name_" + lang),
					result.getString("descr_" + lang),
					getSign(result.getInt("from_unit_id"), lang),
					getSign(result.getInt("to_unit_id"), lang));
			}
		}
	}

	private String getSign(int unitId, String lang) throws SQLException {
		String sql = "SELECT sign_" + lang + " FROM units WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, unitId);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				return result.getString("sign_" + lang);
			}
		}
	}

	static String getLang(String langCode) {
		return switch (langCode) {
			case "fi", "sv", "ja", "ar" -> langCode;
			default -> "en";
		};
	}
}

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

    public List<TemperatureType> getAll() throws SQLException {
        List<TemperatureType> types = new ArrayList<>();
        String sql = "SELECT id, type_name FROM temperature_types ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                types.add(new TemperatureType(result.getInt("id"), result.getString("type_name")));
            }
        }
        return types;
    }
}

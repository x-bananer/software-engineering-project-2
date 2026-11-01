package com.sep.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = System.getProperty("db.url", "jdbc:mariadb://localhost:3306/inclass");
	private static final String USER = System.getProperty("db.user", "inclass");
	private static final String PASSWORD = System.getProperty("db.password", "inclass");

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

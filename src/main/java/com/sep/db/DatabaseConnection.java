package com.sep.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = System.getProperty("db.url", "jdbc:mariadb://localhost:3306/sep_2");
	private static final String USER = System.getProperty("db.user", "root");
	private static final String PASSWORD = System.getProperty("db.password", "1234");

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

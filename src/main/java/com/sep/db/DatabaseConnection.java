package com.sep.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = System.getProperty("db.url", "jdbc:mariadb://" + System.getenv().getOrDefault("DB_HOST", "localhost") + ":" + System.getenv().getOrDefault("DB_PORT", "3306") + "/" + System.getenv().getOrDefault("DB_NAME", "sep_2") + "?useUnicode=true&characterEncoding=utf8");

	private static final String USER = System.getProperty("db.user", System.getenv().getOrDefault("DB_USER", "root"));

	private static final String PASSWORD = System.getProperty("db.password", System.getenv().getOrDefault("DB_PASSWORD", "1234"));

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

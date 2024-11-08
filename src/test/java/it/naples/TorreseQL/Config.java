package it.naples.TorreseQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
	static Connection getConnection(String JDBC) throws SQLException {
		return DriverManager.getConnection(JDBC);
	}
}

package org.web.demo.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static Connection connect = null;
	
	public static Connection openConnection() throws SQLException {
		if (connect == null) {
			connect = DriverManager.getConnection("");
		}
		
		return connect;
	}
	
	public static void closeConnection() throws SQLException {
		if (connect != null) {
			 connect.close();
		}
	}
	
}

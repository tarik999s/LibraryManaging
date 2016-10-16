/**
 * 
 */
package com.botscrew.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Taras Savitskyy
 *
 * Class to create connection with JDBC Driver
 */
public class JDBCHelper {
	
	private Connection conn;

	
	public JDBCHelper(String user, String password, String url)
			throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
	}

	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public PreparedStatement getPreparedStatement(String prepare) {
		try {
			return conn.prepareStatement(prepare);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
}

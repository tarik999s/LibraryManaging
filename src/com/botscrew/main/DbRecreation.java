/**
 * 
 */
package com.botscrew.main;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 * @author Taras Savitskyy
 * 
 */
public class DbRecreation {

	private static void recreation() {
		String recreationQuery = null;
		Connection con = null;
		try {
			recreationQuery = new String(ByteBuffer.wrap(
					Files.readAllBytes(Paths.get(DbRecreation.class
							.getResource(Constants.DUMP_PATH).toURI())))
					.array());
			System.out.print("Connecting to database...");
			con = (Connection) DriverManager.getConnection(Constants.URL,
					Constants.USER_NAME, Constants.PASSWORD);
			System.out.println("Success!");
			System.out.print("Dropping database...");
			con.createStatement().executeUpdate(
					"DROP DATABASE IF EXISTS " + Constants.DB_NAME + ";");
			System.out.println("Success!");
			System.out.print("Creating and selecting new database...");
			con.createStatement().executeUpdate(
					"CREATE DATABASE  IF NOT EXISTS `" + Constants.DB_NAME
							+ "`;");
			con.createStatement().executeUpdate(
					"USE `" + Constants.DB_NAME + "`;");
			System.out.println("Success!");
			System.out.print("Loading dump...");
			Scanner sc = new Scanner(recreationQuery);
			StringBuilder query = new StringBuilder();
			while (sc.hasNextLine()) {
				query.append(sc.nextLine());
				if (query.length() > 0) {
					if (query.charAt(query.length() - 1) == ';') {
						con.createStatement().executeUpdate(query.toString());
						System.out.println(query.toString());
						query = new StringBuilder();
					}
				}
				if (query.length() > 1) {
					if (query.charAt(0) == '-' && query.charAt(1) == '-') {
						query = new StringBuilder();
					}
				}
			}

			System.out.println("Success!");
			System.out.print("Closing connection...");
			con.close();
			System.out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		recreation();
	}

}
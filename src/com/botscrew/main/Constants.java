/**
 * 
 */
package com.botscrew.main;

/**
 * @author Taras Savitskyy
 *
 */
public class Constants {	

	public static final String USER_NAME = "root";
	public static final String PASSWORD = "12345";
	public static final String URL = "jdbc:mysql://localhost:3307/test";
	public static final String DB_NAME = "test";
	public static final String DUMP_PATH = "/dump.sql";
	
	public static final String GET_ALL_BOOKS_QUERY = "SELECT * FROM books ORDER BY name;";
	public static final String GET_BOOK_BY_NAME_QUERY = "SELECT * FROM books WHERE name=?;";
	public static final String ADD_BOOK_QUERY = "INSERT INTO books(name,authorName) VALUES (?,?);";
	public static final String UPDATE_BOOK_QUERY = "UPDATE books SET name=?, authorName=? WHERE id=?;";
	public static final String REMOVE_BOOK_BY_NAME_QUERY = "DELETE FROM books WHERE name=?;";
	public static final String REMOVE_BOOK_BY_ID_AND_NAME_QUERY = "DELETE FROM books WHERE name=? AND id=?;";
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE books (id int, name varchar(50), authorName varchar(50));";
	public static final String CLEAR_TABLE_QUERY = "TRUNCATE TABLE books;";

}

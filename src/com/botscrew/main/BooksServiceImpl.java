/**
 * 
 */
package com.botscrew.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Savitskyy
 * 
 *         Class implements all methods (DB operations)
 */
public class BooksServiceImpl implements BooksService {

	private JDBCHelper helper;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param dbHelper
	 */
	public BooksServiceImpl(JDBCHelper dbHelper) {
		this.helper = dbHelper;
	}

	/**
	 * Add new book to DB table
	 */
	@Override
	public void addBook(Book book) {
		try {
			PreparedStatement pStat = helper.getPreparedStatement(Constants.ADD_BOOK_QUERY);

			pStat.setString(1, book.getName());
			pStat.setString(2, book.getAuthorName());
			pStat.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Edit book values in DB table
	 */
	@Override
	public void editBook(Book oldBook, Book newBook) {
		try {
			PreparedStatement pStat = helper.getPreparedStatement(Constants.UPDATE_BOOK_QUERY);

			pStat.setString(1, newBook.getName());
			pStat.setString(2, newBook.getAuthorName());
			pStat.setInt(3, oldBook.getId());
			pStat.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Method delete value in DB table by Name and Id
	 */
	@Override
	public boolean removeBook(int bookId, String bookName) {
		List<Book> booksList = new ArrayList<Book>();
		try {
		    if (bookId != 0) {
		    	PreparedStatement removeStat = helper.getPreparedStatement(Constants.REMOVE_BOOK_BY_ID_AND_NAME_QUERY);
		    	removeStat.setString(1, bookName);
		    	removeStat.setInt(2, bookId);
		    	removeStat.execute();
		    	removeStat.close();
		        return true;
		    }
		    
		    PreparedStatement pStat = helper.getPreparedStatement(Constants.GET_BOOK_BY_NAME_QUERY);
		    pStat.setString(1, bookName);
            ResultSet resultSet = pStat.executeQuery();
            while (resultSet.next()) {
            	booksList.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            
            if (booksList.size() == 1) {
                PreparedStatement removeStat = helper.getPreparedStatement(Constants.REMOVE_BOOK_BY_NAME_QUERY);
                removeStat.setString(1, bookName);
                removeStat.execute();
                removeStat.close();
                return true;
            }
		   
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}

		return false;
	}

	/**
	 * Method return List of Books from DB table by Name
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<Book> getBookByName(String Name) throws SQLException {
		List<Book> booksList = new ArrayList<Book>();
		PreparedStatement pStat = helper
				.getPreparedStatement(Constants.GET_BOOK_BY_NAME_QUERY);
		try {
			pStat.setString(1, Name);
			ResultSet resultSet = pStat.executeQuery();
			while (resultSet.next()) {
				booksList.add(new Book(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			pStat.close();
		}

		return booksList;
	}

	/**
	 * Method return all Books from DB table.
	 */
	@Override
	public List<Book> getAll() {
		List<Book> booksList = new ArrayList<Book>();
		PreparedStatement pStat = helper.getPreparedStatement(Constants.GET_ALL_BOOKS_QUERY);
		try {
			ResultSet resultSet = pStat.executeQuery();
			while (resultSet.next()) {
				booksList.add(new Book(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return booksList;
	}

	/**
	 * Creates new table 'books' in DB
	 */
	@Override
	public void createTable() {
		PreparedStatement pStat = helper.getPreparedStatement(Constants.CLEAR_TABLE_QUERY);
		try {
			pStat.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Clear all value from table 'books' in DB
	 */
	@Override
	public void clearTable() {
		PreparedStatement pStat = helper.getPreparedStatement(Constants.CLEAR_TABLE_QUERY);
		try {
			pStat.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}

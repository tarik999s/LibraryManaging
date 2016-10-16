/**
 * 
 */
package com.botscrew.main;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Taras Savitskyy
 *
 * Interface contain main DB operations
 */
public interface BooksService {
	
	  public void addBook(Book book);
	  public void editBook(Book oldBook, Book newBook);
	  public boolean removeBook(int bookId, String bookName);
	  public void createTable();
	  public void clearTable();
	  public List<Book> getBookByName(String Name) throws SQLException;
	  public List<Book> getAll();

}

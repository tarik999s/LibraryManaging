/**
 * 
 */
package com.botscrew.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taras Savitskyy
 * 
 */
public class ConsoleSystem {

	static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		System.out.println("Hello it's Library Managment console project!");

		BooksServiceImpl booksService = new BooksServiceImpl(new JDBCHelper(
				Constants.USER_NAME, Constants.PASSWORD, Constants.URL));

		ConsoleManager consoleManager = new ConsoleManager(booksService);

		System.out
				.println("-----------------------------------------------------------");
		System.out.println("See the List of commands:");
		System.out.println("print  - 'Print all books'");
		System.out.println("add    - 'Add new Book'");
		System.out.println("edit   - 'Edit the Book'");
		System.out.println("remove - 'Removes the Book'");
		System.out.println("find   - 'Finds the Book'");
		System.out.println("create - 'Creates the table book'");
		System.out.println("clear  -  'Clear the table'");
		System.out.println("generate [count] - 'Creates a specified number of random books'");
		System.out.println("exit  -  'Exit from project'");
		// System.out.println("Example to write command: " +
		// "add J.K.Roaling-Harry Potter");
		System.out.println("-----------------------------------------------------------");

		String input = scanner.next();

		while (input != "exit") {
			switch (input.split(" ")[0]) {
			case "add":
				consoleManager.addNewBook();
				consoleManager.printAllBooks();
				break;
			case "edit":
				consoleManager.editBook();
				consoleManager.printAllBooks();
				break;
			case "remove":
				consoleManager.removeBook();
				consoleManager.printAllBooks();
				break;
			case "print":
				consoleManager.printAllBooks();
				break;
			case "create":
				booksService.createTable();
				break;
			case "generate":
				consoleManager
						.generateBooks(Integer.parseInt(input.split(" ")[1]));
				consoleManager.printAllBooks();
				break;
			case "clear":
				booksService.clearTable();
				break;
			case "exit":
				System.exit(0);
			}
		}
	}

}

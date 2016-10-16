/**
 * 
 */
package com.botscrew.main;

import java.util.List;
import java.util.Scanner;

/**
 * @author Taras Savitskyy
 * 
 * Class contain method for console system
 */
public class ConsoleManager {

	private Scanner scanner = new Scanner(System.in);
	private BooksServiceImpl booksService;

	/**
	 * CONSTRUCTOR
	 */
	public ConsoleManager(BooksServiceImpl booksService) {
		this.booksService = booksService;
	}

	void addNewBook() {
		System.out.println("Program: Please write name and author in format: [author]-[name], without []");
		try {
			System.out.print("You: ");
			String str = scanner.next();
			String authorName = str.split("-")[0];
			String bookName = str.split("-")[1];

			Book newBook = new Book(0, bookName, authorName);
			booksService.addBook(newBook);
			System.out.println("Program: book " + newBook.toString()
					+ " was succesfully added");
		} catch (Exception e) {
			System.out.println("Program: Unknown command!!!");
		}
	}

	void editBook() {
		System.out.println("Program: Please write name of the book");
		try {
			System.out.print("You: ");
			String str = scanner.next();
			List<Book> booksList = booksService.getBookByName(str);
			if (booksList.isEmpty()) {
				System.out.println("Program: Not found books with such name!");
				return;
			}
			if (booksList.size() == 1) {
				System.out.print("You: ");
				String input = scanner.next();
				String authorName = input.split("-")[0];
				String bookName = input.split("-")[1];

				Book newBook = new Book(0, bookName, authorName);
				booksService.editBook(booksList.get(0), newBook);
				System.out.println("Program: book " + newBook.toString()
						+ " was succesfully edited.");
			} else {
				System.out
						.println("Program: You have a few books with such name please choose one by typing a number of book: ");
				int i = 1;
				for (Book book : booksList) {
					System.out.println("   " + i + ". " + book.toString());
					i++;
				}

				System.out.print("You: ");
				int bookId = scanner.nextInt();
				System.out
						.println("Program: Please write new book name and new author name in such format: author-name");
				String input = scanner.next();
				String authorName = input.split("-")[0];
				String bookName = input.split("-")[1];

				Book newBook = new Book(0, bookName, authorName);
				booksService.editBook(booksList.get(bookId), newBook);
				System.out.println("Program: book " + newBook.toString()
						+ " was succesfully edited");
			}
		} catch (Exception e) {
			System.out.println("Program: Unknown command!!!");
		}
	}

	
	void removeBook() {
		System.out.println("Program: Please write name of the book");
		try {
			System.out.print("You: ");
			String bookName = scanner.next();
			List<Book> booksList = booksService.getBookByName(bookName);

			if (booksList.isEmpty()) {
				System.out.println("Program: book with suck name was not found!");
				return;
			}

			if (booksList.size() == 1) {
				booksService.removeBook(booksList.get(0).getId(), booksList
						.get(0).getName());
				System.out.println("Program: book "
						+ booksList.get(0).toString()
						+ " was succesfully deleted!");
			} else {
				System.out.println("Program: You have a few books with such name please choose one by typing a number of book: ");
				int i = 1;
				for (Book book : booksList) {
					System.out.println("   " + i + ". " + book.toString());
					i++;
				}
				System.out.print("You: ");
				int bookId = scanner.nextInt();
				booksService.removeBook(bookId, bookName);
				System.out.println("Program: book " + booksList.get(bookId - 1)
						+ " was succesfully deleted!");
			}
		} catch (Exception e) {
			System.out.println("Program: Unknown command!");
		}
	}

	
	public void generateBooks(Integer count) {
		int i = 0;
		while (i < count) {
			try {
				String authorName = "Author_" + (i + 1);
				String bookName = "Bookname_" + (i + 1);

				Book newBook = new Book(0, bookName, authorName);
				booksService.addBook(newBook);
			} catch (Exception e) {
				System.out.println("Program: Unknown command!!!");
			}
		}
		System.out.println("Program: " + count
				+ " random books was succesfully added");
	}

	
	void printAllBooks() {
		int i = 1;
		System.out.println("Program: All books ordered by name: ");
		for (Book book : booksService.getAll()) {
			System.out.println("   " + i + ". " + book.toString());
			i++;
		}
	}

}

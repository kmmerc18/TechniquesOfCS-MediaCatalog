/* Displays a JPanel with the most recently added four book buttons from an array list of book objects. Includes functions to
 * update which books are displayed based on what the user inputs to a search bar. */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ListPanel extends JPanel {
	// instance variables
	private ArrayList<Book> books;
	private ArrayList<JButton> bookButtons;
	
	// constructor
	public ListPanel() {
		this.books = new ArrayList<Book>();
		this.bookButtons = new ArrayList<JButton>();
		super.setPreferredSize(new Dimension(200, 300));
		super.setLayout(new GridLayout(4, 1));
	}
	
	// instance methods
	public void addBook(Book newBook) {		// adds a given book to the panel's list of book objects
		books.add(newBook);
		Font buttonFont = new Font("Lucida Sans Typewriter", Font.PLAIN, 15);
		newBook.getListButton().setFont(buttonFont);
		bookButtons.add(newBook.getListButton());	// adds the book's button to the list panel with a given font
		updateList();
		this.invalidate();		// updates the display of the list of book buttons
		this.validate();
	}
	
	public ListPanel updateList() {	// removes all displayed book buttons and displays the current most recent four
		super.removeAll();
		for (int buttonIndex = books.size() - 1; buttonIndex > books.size() - 5 && buttonIndex >= 0; buttonIndex--) {
			super.add(bookButtons.get(buttonIndex));
		}
		return this;
	}
	
	// removes all displayed book buttons and displays the first four from a given list (from the control panel's search)
	public ListPanel updateSearchList(ArrayList<JButton> searchList) {
		super.removeAll();
		for (int buttonIndex = 0; buttonIndex < 4 && buttonIndex < searchList.size(); buttonIndex++) {
			super.add(searchList.get(buttonIndex));
		}
		return this;
	}
	
	// accessors for the panel's instance variables
	public ArrayList<JButton> getbookButtons() {
		return bookButtons;
	}
	
	public Book getBook(int index) {
		return books.get(index);
	}	

	public ArrayList<Book> getBookList() {
		return this.books;
	}
}

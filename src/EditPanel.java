/* Creates a JPanel with four text inputs displaying information about specific Book object so that a user can change this information.
 * Includes a finish button indicating the user has finished inputing this new information. */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel{
	// instance variables
	private JButton finish;
	private JTextField inputTitle;
	private JTextField inputAuthor;
	private JTextField inputGenre;
	private JTextField inputKeyword;
	public Book book;
	
	// constructor
	public EditPanel(Book bookNow) {
		// creates labels with text showing a book's current information for the user to update
		this.book = bookNow;
		inputTitle = new JTextField(book.getTitle());
		inputAuthor = new JTextField(book.getAuthor());
		inputGenre = new JTextField(book.getGenre());
		finish = new JButton("Finish");
		
		// displays the array list of keywords with the book as a string of comma-separated terms 
		String keys = bookNow.getKeywords().get(0);
		if (bookNow.getKeywords().size() > 1) {
			for (int wordIndex = 1; wordIndex < bookNow.getKeywords().size() - 1; wordIndex++) {
				keys = keys + ", " + bookNow.getKeywords().get(wordIndex);
			}
			keys = keys + ", " + bookNow.getKeywords().get(bookNow.getKeywords().size() -1);
		}
		// creates a label with text showing the book's current keywords as established above
		inputKeyword = new JTextField(keys);
		
		super.setPreferredSize(new Dimension(400, 600));
		super.setLayout(new GridLayout(5, 1));
		
		// adds the labels and finish button to the edit panel
		super.add(inputTitle);
		super.add(inputAuthor);
		super.add(inputGenre);
		super.add(inputKeyword);
		super.add(finish);
	}
	
	// instance methods
	// accessors for the panel's instance variables
	public Book getBook() {
		return this.book;
	}
	public JButton getFinish() {
		return this.finish;
	}
	public JTextField getKeyword() {
		return this.inputKeyword;
	}
	public JTextField getAuthor() {
		return this.inputAuthor;
	}
	public JTextField getTitle() {
		return this.inputTitle;
	}
	public JTextField getGenre() {
		return this.inputGenre;
	}
}

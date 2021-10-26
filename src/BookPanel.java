/* Creates a JPanel object that displays all the associated information about a book object, as well as buttons that would return the user
 * to an "initial page" (in this context the list of books), a button leading to an edit screen for the book information, and a buttom
 * that deletes the existence of the book object and its associated panels. */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookPanel extends JPanel {
	// instance methods
	private JLabel title;
	private JLabel author;
	private JLabel genre;
	private JLabel keyword;
	private JButton edit;
	private JButton remove;
	private Book book;
	private JButton initialPage;
	
	// constructor
	public BookPanel(Book book) {
		this.book = book;
		
		// creates, formats font, and centers labels for the book's information on title, author, genre, and keywords
		this.title = new JLabel(book.getTitle());
		Font titleFont = new Font("Lucida Bright", Font.BOLD + Font.ITALIC, 35);
		title.setFont(titleFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		this.author = new JLabel("by " + book.getAuthor());
		Font authorFont = new Font("Lucida Bright", Font.BOLD, 25);
		author.setFont(authorFont);
		author.setHorizontalAlignment(JLabel.CENTER);
		
		this.genre = new JLabel("Genre: " + book.getGenre());
		Font genreFont = new Font("Lucida Bright", Font.PLAIN, 20);
		genre.setFont(genreFont);
		genre.setHorizontalAlignment(JLabel.CENTER);
		
		String keys = book.getKeywords().get(0);
		if (book.getKeywords().size() > 1) {
			for (int wordIndex = 1; wordIndex < book.getKeywords().size() - 1; wordIndex++) {
			keys = keys + ", " + book.getKeywords().get(wordIndex);
			}
			keys = keys + ", " + book.getKeywords().get(book.getKeywords().size() - 1);
		}
		this.keyword = new JLabel("<html>Keywords:<br/> " + keys + "</html>");
		Font keywordFont = new Font("Lucida Bright", Font.PLAIN, 15);
		keyword.setFont(keywordFont);
		
		// establishes the edit, remove, and return to list buttons as new JButtons
		edit = new JButton("Edit");
		remove = new JButton("Remove");
		initialPage = new JButton("Back to List");
		
		// adds the buttons and labels to the panel
		super.setPreferredSize(new Dimension(400, 600));
		super.setLayout(new GridLayout(7, 1));
		super.add(title);
		super.add(author);
		super.add(genre);
		super.add(keyword);
		super.add(edit);
		super.add(remove);
		super.add(initialPage);
	}
	
	// instance methods
	// accessors for the panel's instance variables
	public Book getBook() {
		return book;
	}
	
	public JLabel getTitle() {
		return this.title;
	}
	
	public JLabel getAuthor() {
		return this.author;
	}
	
	public JLabel getGenre() {
		return this.genre;
	}
	
	public JLabel getKeywords() {
		return this.keyword;
	}
	
	public JButton getEdit() {
		return this.edit;
	}
	
	public JButton getRemove() {
		return this.remove;
	}
	public JButton getinitialPage() {
		return this.initialPage;
	}
}

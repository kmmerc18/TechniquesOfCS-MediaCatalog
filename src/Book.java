/* Creates a book object with associated title, author, genre, and keywords. Also gives the book associated display panels for the viewing 
 * of its information and the editing of its information, as well as an associated button that will appear in ListPanel. */

import java.util.ArrayList;
import javax.swing.JButton;

public class Book {
	// instance variables
	private String title;
	private String author;
	private String genre;
	private ArrayList<String> keyWords;
	private EditPanel editPanel;
	private BookPanel booksPanel;
	private JButton listButton;
	
	
	// constructor
	public Book(String title, String author, String genre, String keywords) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		keyWords = new ArrayList<String>();
		changeKeywords(keywords);
		this.editPanel = new EditPanel(this);
		this.booksPanel = new BookPanel(this);
		this.listButton = new JButton(this.title);
	}
	
	// instance methods
	// setters for the book's instance variables
	public void changeTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public void changeAuthor(String newAuthor) {
		this.author = newAuthor;
	}
	
	public void changeKeywords(String newWords) {
		keyWords.clear();
		String[] eachWord = newWords.split(",\\s");
		for (int keywordIndex = 0; keywordIndex < eachWord.length; keywordIndex++) {
			keyWords.add(keywordIndex, eachWord[keywordIndex]);
		}
	}
	public void changeGenre(String newGenre) {
		this.genre = newGenre;
	}
	
	// accessors for the book's instance variables
	public String getTitle() {
		return this.title;
	}
	
	public EditPanel getPanelEdit() {
		return this.editPanel;
	}
	public BookPanel getPanelBook() {
		return this.booksPanel;
	}
	public JButton getListButton() {
		return this.listButton;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public ArrayList<String> getKeywords() {
		return this.keyWords;
	}
}

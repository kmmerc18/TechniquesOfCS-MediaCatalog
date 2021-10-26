/* Creates a JPanel with four text inputs for the user so they can input information about a Book object they'd like to create.
 * Includes a finish button indicating the user has finished inputing this new information. */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel {
	// instance variables
	private JButton finish;
	private JTextField inputTitle;
	private JTextField inputAuthor;
	private JTextField inputGenre;
	private JTextField inputKeyword;
	
	// constructor
	public AddPanel() {
		// initialize the button and text fields of the add panel
		finish = new JButton("Finish");
		inputTitle = new JTextField("Title...");
		inputAuthor = new JTextField("Author...");
		inputGenre = new JTextField("Genre...");
		inputKeyword = new JTextField("Keyword...");
		
		super.setPreferredSize(new Dimension(400, 600));
		super.setLayout(new GridLayout(5, 1));
		
		// add the button and text fields to the panel
		super.add(inputTitle);
		super.add(inputAuthor);
		super.add(inputGenre);
		super.add(inputKeyword);
		super.add(finish);
	}
	
	// instance methods
	// accessors for the panel's instance variables
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

/* Creates a JPanel with a button for adding books to the catalog, a search bar, a search button, and a ListPanel listing book titles
 * previously entered. */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitialPanel extends JPanel {
	// instance variables
	private JButton add;
	private JButton search;
	private JTextField inputSearch;
	private ListPanel Lister;
	
	// constructor
	public InitialPanel() {
		this.add = new JButton("+ Add");		// initializes the instance variables
		this.search = new JButton("Search");
		this.inputSearch = new JTextField("Enter keyword...");
		this.Lister = new ListPanel();
		
		super.setPreferredSize(new Dimension(400, 600));	// sets the dimensions of the JPanel and the layout
		super.setLayout(new GridLayout(5, 1));				
		super.add(add);										// adds the associated buttons, text field, and list
		super.add(inputSearch);								// to the panel
		super.add(search);
		super.add(Lister);
	}
	
	// instance methods
	// accessors for all of the panel's instance variables
	public JButton getadd() {
		return add;
	}
	public JButton getsearch() {
		return search;
	}
	public ListPanel getListPanel() {
		return this.Lister;
	}
	public JTextField getinputSearch() {
		return this.inputSearch;
	}
}

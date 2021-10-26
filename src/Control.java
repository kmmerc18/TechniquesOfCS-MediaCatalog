/* Panel allows the communication of all the buttons of every other panel (ever book panel, edit panel, list panel, add panel, 
 * and initial panel). Performs the function of taking information from the user's typed entries and button-clicking and gives it to the 
 * correct panels and methods to create books, display their information, edit them, search for them, and remove them. */

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {
	// instance variables
	private ArrayList<EditPanel> EditPanelList;
	private ArrayList<BookPanel> BookPanelList;
	private AddPanel Add; 
	private ListPanel listPanel;
	private InitialPanel Initial;
	private JPanel thePanel;
	private JFrame frame;
	private JButton returntoList;

	// constructor
	public Control(JFrame frame) {							// creates the control panel for a specific JFrame
		this.frame = frame;									// initializes the array lists of edit panels and book panels
		this.EditPanelList = new ArrayList<EditPanel>();
		this.BookPanelList = new ArrayList<BookPanel>();
		this.Add = new AddPanel();							// initializes the add panel
		this.Initial = new InitialPanel();					// creates the initial panel and adds control as an action listener
		Add.getFinish().addActionListener(this);			// for all its input sources
		Add.getTitle().addActionListener(this);
		Add.getAuthor().addActionListener(this);
		Add.getGenre().addActionListener(this);
		Add.getKeyword().addActionListener(this);
		Initial.getadd().addActionListener(this);
		Initial.getsearch().addActionListener(this);
		Initial.getinputSearch().addActionListener(this);
		this.listPanel = Initial.getListPanel();			// makes the initial panel's list panel known to control
		thePanel = Initial;
		this.returntoList = new JButton("Return to List");	// creates a return to list button but does not add it to Initial yet
	}

	// instance methods
	public void addBooks(Book newBook) {					// method to add a Book to the list panel list
		listPanel.addBook(newBook);
		newBook.getListButton().addActionListener(this);	// makes control the action listener for the book's button in list

		BookPanelList.add(newBook.getPanelBook());			// add the book's book panel to control's array list of book panels
		newBook.getPanelBook().getEdit().addActionListener(this); // adds control as an action listener for this panel's inputs
		newBook.getPanelBook().getRemove().addActionListener(this);
		newBook.getPanelBook().getinitialPage().addActionListener(this);

		EditPanelList.add(newBook.getPanelEdit());			// add the book's edit panel to control's array list of edit panels
		newBook.getPanelEdit().getFinish().addActionListener(this); // adds control as an action listener for this panel's inputs
		newBook.getPanelEdit().getKeyword().addActionListener(this);
		newBook.getPanelEdit().getAuthor().addActionListener(this);
		newBook.getPanelEdit().getGenre().addActionListener(this);
		newBook.getPanelEdit().getTitle().addActionListener(this);

		Add = new AddPanel();								// makes the add panel a blank add panel again
		Add.getFinish().addActionListener(this);			// adds control as an action listener for the add panel
	}

	public void editBooks(EditPanel currentEdit) {			// changes information for a specific book object given user input
		Book currentBook = currentEdit.getBook();			// from an edit panel
		currentBook.changeTitle(currentEdit.getTitle().getText());
		currentBook.changeAuthor(currentEdit.getAuthor().getText());
		currentBook.changeGenre(currentEdit.getGenre().getText());
		currentBook.changeKeywords(currentEdit.getKeyword().getText());

		currentBook.getListButton().setText(currentBook.getTitle());
		currentBook.getPanelBook().getTitle().setText(currentBook.getTitle());
		currentBook.getPanelBook().getAuthor().setText("by " + currentBook.getAuthor());
		currentBook.getPanelBook().getGenre().setText("Genre: " + currentBook.getGenre());
		
		// creates an updated keyword JLabel for the book panel
		String keywordLabel = "<html>Keywords: <br/>" + currentBook.getKeywords().get(0);
		for (int keywordIndex = 1; keywordIndex < currentBook.getKeywords().size() - 1; keywordIndex++) {
			keywordLabel = keywordLabel + ", " + currentBook.getKeywords().get(keywordIndex);
		}
		keywordLabel = keywordLabel + ", " + currentBook.getKeywords().get(currentBook.getKeywords().size() - 1) + "</html>";
		currentBook.getPanelBook().getKeywords().setText(keywordLabel);
	}

	public void removeBooks(Book book) {			// removes a book and all it's panels from control and list panel's lists
		EditPanelList.remove(book.getPanelEdit());
		BookPanelList.remove(book.getPanelBook());
		listPanel.getbookButtons().remove(book.getListButton());
		listPanel.getBookList().remove(book);
		listPanel.updateList();
		if (Initial.getComponent(4) == returntoList) {	// if remove book was pressed after searching a word, remove the 
			Initial.remove(returntoList);				// return to list option when returning to the initial list screen after
		}
	}

	// changing thePanel
	public void toEdit(Book bookNow) {		// displays the edit panel for a given book
		thePanel = bookNow.getPanelEdit();
		frame.setContentPane(thePanel);
		frame.invalidate();
		frame.validate();
	}

	public void toAdd() {				// displays the blank add panel
		thePanel = Add;
		frame.setContentPane(thePanel);
		frame.invalidate();
		frame.validate();
	}

	public void toInitial() {			// displays the initial panel
		thePanel = Initial;
		frame.setContentPane(thePanel);
		frame.invalidate();
		frame.validate();
	}
	
	public void toSearchResults() {		// updates the list panel to show search results rather than the main list
		Initial.add(this.returntoList); // adds a button to return to the main list and makes control the action listener for this
		returntoList.addActionListener(this);
		toInitial();
	}

	public void toBook(Book book) {		// displays the book panel for a given book
		thePanel = book.getPanelBook();
		frame.setContentPane(thePanel);
		frame.invalidate();
		frame.validate();
	}

	public JPanel getThePanel() {		// allows the main program BookCase to access the panel control wants displayed
		return thePanel;
	}

	// action listening
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == Initial.getadd()) {	// checks if the add button has been pressed from the initial panel
			toAdd(); 									// displays the
		} 
		
		else if (event.getSource() == this.returntoList) {	// checks if the return to list button has been pressed from the initial panel
			Initial.remove(returntoList);					// (which is only present if the user entered a search term)
			listPanel.updateList();
			toInitial();									// returns user to initial panel
		}

		else if (event.getSource() == Initial.getsearch()) {		// checks if the search button was pressed from the initial panel
			String searchTerm = Initial.getinputSearch().getText(); // creates a variable for the search term entered by the user
			ArrayList<Book> resultBooks = new ArrayList<Book>();	// creates a new array list for book objects that fit the results

			// looks through each book in the list panel array list of books
			for (int bookIndex = 0; bookIndex < listPanel.getBookList().size(); bookIndex++) { 
				Book searchBook = listPanel.getBookList().get(bookIndex);

				// adds the book to the result list if the title contains the term entered by the user
				if (searchBook.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
					resultBooks.add(searchBook);
				} 
				
				// adds the book to the result list if the author contains the term entered by the user
				else if (searchBook.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
					resultBooks.add(searchBook);
				} 

				// adds the book to the result list if the genre contains the term entered by the user
				else if (searchBook.getGenre().toLowerCase().contains(searchTerm.toLowerCase())) {
					resultBooks.add(searchBook);
				} 

				// adds the book to the result list if the keyword list contains the term entered by the user
				else {
					for (int keywordIndex = 0; keywordIndex < listPanel.getBookList().get(bookIndex).getKeywords().size(); keywordIndex++) {
						if (searchBook.getKeywords().get(keywordIndex).toLowerCase().contains(searchTerm.toLowerCase())) {
							resultBooks.add(searchBook);
						}
					} 				
				}
			}

			// takes the books in the search results and adds their buttons (in order oldest to newest) to the list panel on the initial panel
			ArrayList<JButton> searchResults = new ArrayList<JButton> ();		// puts the JButtons of each book returned in the keyword search
			for (int resultIndex = 0; resultIndex < resultBooks.size(); resultIndex++) {	// in a list to be displayed in the list panel
				for (int search = 0; search < listPanel.getBookList().size(); search++) {
					if (resultBooks.get(resultIndex) == listPanel.getBookList().get(search)) {
						searchResults.add(listPanel.getbookButtons().get(search));
						break;
					}
				}
			}

			Initial.getinputSearch().setText("Enter Keyword...");		// resets the search bar text
			listPanel.updateSearchList(searchResults);					// updates the book list displayed on the initial panel
			toSearchResults();
		}

		else if (event.getSource() == Add.getFinish()) {			// checks if an add panel's finish button was selected and creates a new book
			Book newBook = new Book(Add.getTitle().getText(), Add.getAuthor().getText(),  // object with the information in the add panel text
					Add.getGenre().getText(), Add.getKeyword().getText());		// fields before creating a new add panel and returning the 
			addBooks(newBook);													// user to the initial page
			toInitial();			
		}

		for (int bookIndex = 0; bookIndex < listPanel.getBookList().size(); bookIndex++) {	// looks through every book in the list's index
			if (event.getSource() == listPanel.getbookButtons().get(bookIndex)) {	// checks if the book's button is pressed from the list panel
				toBook(listPanel.getBook(bookIndex));								// opens the book panel displaying that book's information
				break;
			} else if (event.getSource() == BookPanelList.get(bookIndex).getEdit()) {	// checks if a book panel's edit button is selected
				toEdit(BookPanelList.get(bookIndex).getBook());							// opens the edit panel with that book's information
				break;
			}	else if (event.getSource() == EditPanelList.get(bookIndex).getFinish()) {	// checks if an edit panel's finish button is selected
				editBooks(EditPanelList.get(bookIndex));							// changes the information of the book whose edit panel it was
				toBook(EditPanelList.get(bookIndex).getBook());						// returns user to the book information page
				break;
			} else if (event.getSource() == BookPanelList.get(bookIndex).getRemove()) {	// checks if a book panel's remove button was selected
				removeBooks(BookPanelList.get(bookIndex).getBook());					// removes the book entry and associated information
				toInitial();															// returns the user to the list page of other entries
				break;
			} else if (event.getSource() == BookPanelList.get(bookIndex).getinitialPage()) { // checks if a book panel's return to initial page
				Initial.remove(returntoList);				// button was selected and removes this button before reloading and updating
				listPanel.updateList();						// the initial page and displaying it
				toInitial();
				break;
			}
		}
	}
}

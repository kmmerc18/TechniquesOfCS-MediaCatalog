/* Runs the program that allows a user to create a temporary database of books with titles, keywords, authors, 
 * and genres and allows them to search and alter this list */

import javax.swing.JFrame;

public class BookCase {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("BookCase");	// creates a display JFrame					
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 // Program will end when frame is closed
		Control Display = new Control(myFrame);		// attaches a control object to the frame to run the program
		
		myFrame.getContentPane().add(Display.getThePanel()); // displays the panel the control frame establishes
		myFrame.pack();												
		myFrame.setVisible(true);	
	}
}

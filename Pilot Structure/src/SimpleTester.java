/**
 * This is the main class of this project
 */

import com.perisic.beds.*;
/**
 * Tests the recycling machine.
 * @author Marc Conrad
 *the main class
 */
public class SimpleTester {
	
	public static void main(String [] args) throws Exception { 
		/*
		 * an object for the display class is created
		 */
		Display myDisplay = new Display(); 
		/*
		 * an object of CustomerPanel class is created
		 */
		CustomerPanel myPanel = new CustomerPanel(myDisplay); 
		
		/*
		 * the created object is used to print the items on the receipt
		 */
		myPanel.itemReceived(1);
		myPanel.itemReceived(1);
		myPanel.itemReceived(3);
		myPanel.itemReceived(2);
		myPanel.itemReceived(4);
		myPanel.itemReceived(4);
		myPanel.itemReceived(5);
		myPanel.printReceipt();
	}
}

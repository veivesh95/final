package com.perisic.beds;

import javax.swing.*;

/**
 * This boundary class initiates the System This is the interface of the system.
 * It represents the interaction from the customer with the system. The customer
 * panel knows the recycling machine (i.e. the Deposit Item Receiver class)
 *
 * @author Veivesh Krishnakumar
 */

public class CustomerPanel {
	DepositItemReceiver receiver = null;

	/**
	 * 
	 * @param printer
	 */
	public CustomerPanel(PrinterInterface printer) {
		super();
		receiver = new DepositItemReceiver(printer);
	}

	/**
	 * @param slot
	 *            - item (Cans/bottle/crate/paperbag) has been entered into the
	 *            system.
	 * 
	 */
	public void itemReceived(int slot) {
		receiver.classifyItem(slot);
	}

	/**
	 * method that holds the receipt content to be displayed
	 */
	public void printReceipt() {
		receiver.printReceipt();
	}

	/**
	 * String return of printReceipt output to display on the JFrame window
	 *
	 * @return String of receipt object
	 */
	public String guiReceipt() {
		if (receiver.theReceiptBasis == null) {
			JOptionPane.showMessageDialog(null, "Try adding some stuffs before printing a receipt");
			return null;
		} else {
			// DepositItemReceiver.sum_weight = 0;
			RecyclingGUI.progressBar.setValue(DepositItemReceiver.sum_weight);
			return receiver.stringReceipt();
		}
	}

	/**
	 * Button click event method of Clearing the cart
	 */
	public void clearReceipt() {
		try {
			JOptionPane.showMessageDialog(null, "Cart cleared :)");
			receiver.clear();
			// if (receiver.theReceiptBasis == null) {
			// JOptionPane.showMessageDialog(null, "Your cart is empty");
			// } else {
			// JOptionPane.showMessageDialog(null, "Cart cleared :)");
			// receiver.clear();
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * To reset the machine, by nullify the receiptBasis  
	 */
	public void resetMachine() {
		receiver.resetReceiptBasis();
	}

	/**
	 * this method checks whether the machine has any items or not
	 * @return boolean if it has any items in it.
	 */
	public boolean machineHasItems() {
		if (receiver.theReceiptBasis == null) {
			return true;
		} else
			return false;
	}

	/**
	 * This method will be accessed by HQ
	 * @return the number of items 
	 */
	public int getNumberOfItems() {
		return receiver.getNumberOfItems();
	}
}

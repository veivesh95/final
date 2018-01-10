package com.perisic.util;

import com.perisic.beds.CustomerPanel;

/**
 * This class acts as the bridge between server side and client side
 * RecyclingService class holds the method calls which needs to be run via the
 * Server side for its own use
 * 
 * @author Veivesh Krishnakumar
 *
 */
public class RecyclingService {

	private String sessionCookie = "NotSet" + Math.random();
	CustomerPanel myRecyclingMachine = null;

	/**
	 * running an instance of CustomerPanel when their is a need to reset the
	 * CustomerPanel
	 * 
	 * @param myPanel
	 */
	public void setCustomerPanel(CustomerPanel myPanel) {
		myRecyclingMachine = myPanel;
	}

	/**
	 * gets number of items which were added by a specific machine
	 * 
	 * @param mySessionCookie
	 *            is the identifier of a particular Client machine
	 * @return the amount which we received from getNumberOfItems method which
	 *         returns the amount of total items in the machine
	 */
	public int getNumberOfItems(String mySessionCookie) {
		// We have to implement something here. For the moment, just assume
		// there will
		// be four items in the machine.
		if (mySessionCookie.equals(sessionCookie)) {
			return myRecyclingMachine.getNumberOfItems();
		} else {
			return -1;
		}
	}

	/**
	 * Reset function is to reset all the current items details in the cart
	 * 
	 * @return String notifier saying the Values are being reset.
	 */
	public String reset() {
		myRecyclingMachine.clearReceipt();
		return "Values has been reset.";
	}

	/**
	 * To get an overview of whether the head office has been connected with the
	 * client machine
	 * 
	 * @return string notifier
	 */
	public String testConnection() {
		return "The connection is fine! Thank you!";
	}

	/**
	 * Login method which returns to head office to access to the client machine
	 * 
	 * @param password
	 *            the String value received from the messagebox of head office
	 *            parent gui
	 * @return assigns cookie so it can later on access the other methods or if
	 *         the login fails returns a error notifier
	 */
	public String login(String password) {
		if (password.equals("123")) { // Needs some more sophisticated mechanism
										// such encryption and database etc
										// etc!!!!
			sessionCookie = "Cookie" + Math.random();
			return sessionCookie;
		} else {
			return "not ok"; // Let's come to this point later.
		}
	}

	/**
	 * to head office logging out from the client machine
	 * @return string notifier
	 */
	public String logout() {
		sessionCookie = "NotSet" + Math.random();
		return "logout ok";
	}
}

package com.perisic.beds;

import javax.swing.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * This is where the data lives, i.e. cans, bottles and crates are recorded in
 * this class. We might call it our database (if we insist!). It also provides a
 * summative statement about all the items inserted into the machine.
 * 
 * @author Marc Conrad
 *
 */

public class ReceiptBasis {
	private Vector<DepositItem> myItems = new Vector<DepositItem>();

	static int canCounter = 0;
	static int bottleCounter = 0;
	static int paperbagCounter = 0;
	static int crateCounter = 0;
	static int totalCount = 0;

	private static String fileName = null;
	public static String filePath = System.getProperty("user.dir");

	String receipt = null;
	String timeStamp = new SimpleDateFormat("yyyy/MM/dd -- HH:mm:ss").format(Calendar.getInstance().getTime());

	/**
	 * @param item type from DepositItem Class
	 * 
	 */
	public void addItem(DepositItem item) {
		// myItems.add(item);
		// item.number = myItems.indexOf(item);
		// System.out.println();

		myItems.add(item);
		// RecyclingGUI.myMachineItems.add(item);
		item.number = myItems.indexOf(item);
		// addItemMyMachineItem( item.value, item.name);

		String name = item.name;
		int value = item.value;
		Vector addItemToServerVector = new Vector();
		addItemToServerVector.add(name);
		addItemToServerVector.add(value);
		addItemToServerVector.add(RecyclingGUI.clientIP);
		try {
			Object results = RecyclingGUI.connectToServer.execute("serverHandler.addItem", addItemToServerVector);
			System.out.println("Response from Server: " + results.toString());
			// JOptionPane.showMessageDialog(null, results.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Response from Server: FAILED - " + e);
		}
	}

	
	/**
	 * Calculates a summary based on the items inserted.
	 * @return the overall value of the items inserted by the customer.
	 * @return receipt format )total sum to PrintReceipt interface
	 * 
	 */
	public String computeSum() {
		String receipt = "";
		int sum = 0;
		for (int i = 0; i < myItems.size(); i++) {
			DepositItem item = myItems.get(i);
			int itemNumber = item.number + 1; // to start the number of items in the bill from 1 instead of 0
			String itemName = item.getClass().getSimpleName(); // items Name from class name
			receipt = receipt + itemNumber + ".  " + itemName + ":  " + item.value;
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value;

			if (item.name.equals("Can")) {
				canCounter += 1;
			}

			if (item.name.equals("Bottle")) {
				bottleCounter += 1;
			}

			if (item.name.equals("Crate")) {
				crateCounter += 1;
			}

			if (item.name.equals("Paper_Bag")) {
				paperbagCounter += 1;
			}

			if (item.name.equals("Plastic_Bottle")) {
				bottleCounter += 1;
			}

			if (item.name.equals("Glass_Bottle")) {
				bottleCounter += 1;
			}

		}
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Transaction Receipt";
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + timeStamp;
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + " --------------------------------";
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Number of Cans: " + canCounter + System.getProperty("line.separator");
		receipt = receipt + "Number of Bottles: " + bottleCounter + System.getProperty("line.separator");
		receipt = receipt + "Number of Paperbags: " + paperbagCounter + System.getProperty("line.separator");
		receipt = receipt + "Number of Crates: " + crateCounter + System.getProperty("line.separator");
		receipt = receipt + " --------------------------------";

		receipt = receipt + "\nTotal: " + sum;
		totalCount = sum;
		transactionFile(System.getProperty("line.separator") + System.getProperty("line.separator") + receipt
				+ System.getProperty("line.separator") + System.getProperty("line.separator"));
		return receipt;
	}
	
	
	/**
	 * Clear method!
	 * This is what resets the whole app during
	 * Engineer's request
	 * 
	 */
	public void clear() {
		if (isEmpty(myItems)) {
			JOptionPane.showMessageDialog(null, "Empty Vector list");
		} else {
			myItems.clear();
			DepositItemReceiver.sum_weight = 0;
			RecyclingGUI.progressBar.setValue(DepositItemReceiver.sum_weight);
			transactionFile(System.getProperty("line.separator") + "********* Machine Reset at " + timeStamp
					+ " *********" + System.getProperty("line.separator"));
		}
	}

	
	/**
	 * general function to get today's date using Java library for
	 * date and time and shortened the date format for
	 * the file name
	 * @return Today's date in shortened method
	 */
	public static String getDate() {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return date.format(new Date());
		} catch (Exception ex) {
			System.out.println("getDate function error :" + ex);
			return null;
		}
	}

	
	/**
	 * non returning method, used to print the transaction result on 
	 * a text file and store it under receipt folder under the project
	 * structure
	 * @param params is the bill format which we pass through the 
	 * computeSum method to display the bill
	 */
	private void transactionFile(String params) {
		BufferedWriter writer = null;
		fileName = getDate();
		try {
			writer = new BufferedWriter(new FileWriter(filePath + "\\receipt\\" + fileName + ".txt", true));
			writer.write(params);
			System.out.println("\nFile output SUCCESS");
		} catch (Exception ex) {
			System.out.println("File output FAILED: " + ex);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Method to check whether the vector list is empty or not
	 * @param v for Vector which is passed via the parameter
	 * @return if it's empty it will return true, else false
	 */
	private boolean isEmpty(Vector<DepositItem> v) {
		if (v == null) {
			return true;
		} else
			return false;
	}
}

package com.perisic.beds.client;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class that provides some predefined functionalities to the reach of clients
 * including Logging in to the system and assigning a cookie for an authenticated client
 * @author Veivesh Krishnakumar
 *
 */
public class ServiceProvider {

	private String sessionCookie = "NotSet" + Math.random();
	public static int numberOfClients = 0;
	public static Vector connectedClients = new Vector();
	public static Vector depositedItems = new Vector();

	static String nameOfItem;
	static int numberOfItem;
	static int valueOfItem;
	static String clientMachineIP;
	static String dateOfTransaction;

	/**
	 * Provides the value of each and every item which has been requested through client 
	 * application
	 * @param itemName as the parameter which passes the requested item's name through client program
	 * @return the value of requested item
	 */
	public int valueProvider(String itemName) {
		return new ValueProvider().getValue(itemName);
	}

	
	/**
	 * Method that assigns random cookie to the clients to maintain session between HQ and client machnine 
	 * And also increases the number of clients which are connected
	 * to the headquarters.
	 * @return random session cookie
	 */
	public String getConnection() {
		sessionCookie = "Cookie" + Math.random();
		numberOfClients++;
		return sessionCookie;
	}
	
	/**
	 * Method that disposes currently logged in client and  removes their 
	 * session cookie by overwriting them with specified token. 
	 * and decreases the current amount of clients which are connected to the Server right now.
	 */
	public void logout() {
		sessionCookie = "NotSet" + Math.random();
		numberOfClients--;
	}

	/**
	 * Method which holds each and every items it has passed through from 
	 * RecycleGUI so that by using this Vector it can display the machine item summary later on.
	 * @param itemName as the item's name which is passed from the Recycling GUI
	 * @param itemValue refers the value of item by the time it was added in to the system
	 * @param clientIP as the IP address which is received through the Client's program.
	 * @return a confirmation that items are being added in to this data structure so it can 
	 * be accessed later to generate a summary of items.
	 */
	public String addItem(String itemName, int itemValue, String clientIP) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		// System.out.println(formatter.format(date));

		String[] tempArray = new String[5];
		nameOfItem = itemName;
		valueOfItem = itemValue;
		numberOfItem = depositedItems.size() + 1;
		clientMachineIP = clientIP;
		dateOfTransaction = formatter.format(date);
		
		tempArray[0] = Integer.toString(numberOfItem);
		tempArray[1] = dateOfTransaction; 
		tempArray[2] = nameOfItem;
		tempArray[3] = Integer.toString(itemValue);
		tempArray[4] = clientMachineIP;
		

		depositedItems.add(tempArray);
		return itemName + " Successfully added";
	}

}

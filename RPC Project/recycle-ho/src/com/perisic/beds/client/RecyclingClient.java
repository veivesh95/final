package com.perisic.beds.client;

import java.util.*;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.*;

/**
 * Class which creates a connection through port number 5001 to access the Server which hosts in that 
 * exact port. As a confirmation it runs the TestConnection and GetNumberOfItems.
 * @author Veivesh Krishnakumar
 *
 */
public class RecyclingClient {
	public static void main(String[] args) {
		try {
			XmlRpcClient server = new XmlRpcClient("http://localhost:5001/RPC2"); //
			Object result1 = server.execute("clientHandler.testConnection", new Vector());
			System.out.println("Received: " + result1.toString() + ".");
			Object result = server.execute("clientHandler.getNumberOfItems", new Vector());
			System.out.println("There are " + result.toString() + " items in the machine.");
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}

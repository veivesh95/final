package com.perisic.beds;

/**
 * Represents a Can that is inserted into the recycling machine.
 */

import java.util.Vector;

/**
 * This entity class returns the weight, size and value for a can. Extends
 * DepositItem class
 * 
 * @author Veivesh Krishnakumar
 *
 */
public class Can extends DepositItem {
	static int weight = 4;
	static int size = 5;

	/**
	 * Method returns the value of a can
	 */
	public Can() {
		try {
			name = "Can";
			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);
			Object itemValueResult = RecyclingGUI.connectToServer.execute("serverHandler.valueProvider",
					itemNameVector);
			value = Integer.parseInt(itemValueResult.toString());
		}

		catch (Exception ex) {
			System.out.println("Hello");
		}
	}
}

package com.perisic.beds;

import java.util.Vector;

/**
 * This entity class returns the weight, size and value for a crate. Extends
 * DepositItem class
 * 
 * @author Veivesh
 *
 */
public class Crate extends DepositItem {
	static int weight = 15;
	static int size = 90;

	/**
	 * Method returns the value of a crate
	 */
	public Crate() {
		try {
			name = "Crate";

			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);
			Object itemValueResult = RecyclingGUI.connectToServer.execute("serverHandler.valueProvider",
					itemNameVector);
			value = Integer.parseInt(itemValueResult.toString());

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

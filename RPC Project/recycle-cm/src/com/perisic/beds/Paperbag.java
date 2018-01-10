package com.perisic.beds;

import java.util.Vector;

/**
 * Developed on Week 2 This entity class returns the weight, size and value for
 * a paperbag. Extends DepositItem class
 * @author Veivesh Krishnakumar
 */
public class Paperbag extends DepositItem {

	static int weight = 2;
	static int size = 10;

	/**
	 * Returns the value of a paperbag (15) and the name of the item
	 */
	public Paperbag() {
		try {
			name = "Paper_Bag";

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

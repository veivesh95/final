package com.perisic.beds;

import java.util.Vector;

/**
 * This class extends non-abstract class Bottle.java
 * @author Veivesh Krishnakumar
 */

public class Glass_Bottle extends Bottle {

	static int size = 10;
	static int weight = 15;

	/**
	 * Returns the name of the item and the value of it
	 */
	public Glass_Bottle() {

		try {
			name = "Glass_Bottle";

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

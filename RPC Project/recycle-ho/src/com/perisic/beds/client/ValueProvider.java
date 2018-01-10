package com.perisic.beds.client;

/**
 * Class which is created to get and set the values of items and host them through Headquarters.
 * This slightly refers to the previous version of ItemReader.java class which 
 * gets the value and set the value through Prices.PHP file which was given to us in the beginning of this project time line.
 * @author Veivesh Krishnakumar
 *
 */
public class ValueProvider {
	
	/**
	 * Values are declared with static so it can be changed when the Admin wants it to be changed later on.
	 */
	static int valCan = 20;
	static int valCrate = 40;
	static int valGBottle = 30;
	static int valPBottle = 15;
	static int valPBag = 10;

	/**
	 * Static method which can be accessed to the Client service hosting to pass the value of each and every time
	 * on request from Recycle GUI
	 * @param itemName passed parameter of required item's name from client
	 * @return value of that specific item.
	 */
	public static int getValue(String itemName) {
		if (itemName.equals("Can"))
			return valCan;
		else if (itemName.equals("Crate"))
			return valCrate;
		else if (itemName.equals("Glass_Bottle"))
			return valGBottle;
		else if (itemName.equals("Plastic_Bottle"))
			return valPBottle;
		else if (itemName.equals("Paper_Bag"))
			return valPBag;
		else
			return 0;
	}

	/**
	 * Static method which is being used to set the value of an item passed 
	 * through admin within the same workspace 
	 * @param itemName to check on the correct item's name that it's value need to be changed
	 * @param value that needs to be change the existing value.
	 */
	public static void setValue(String itemName, int value) {
		if (itemName.equals("Can"))
			valCan = value;
		else if (itemName.equals("Crate"))
			valCrate = value;
		else if (itemName.equals("Glass_Bottle"))
			valGBottle = value;
		else if (itemName.equals("Plastic_Bottle"))
			valPBottle = value;
		else if (itemName.equals("Paper_Bag"))
			valPBag = value;
	}
}

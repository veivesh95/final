package com.perisic.beds;

import org.apache.xmlrpc.*;

import java.util.Vector;

/**
 * This represents an item that has been inserted into the recycling machine.
 *
 * Parent class and an abstract class which holds the item's properties
 * 
 * @author Veivesh Krishnakumar
 *
 */

public abstract class DepositItem {
	/**
	 * The running number when the item was inserted.
	 */
	int number;

	/**
	 * the value of the item.
	 */
	int value;

	/**
	 * Returns the name of the item.
	 */
	String name;
}

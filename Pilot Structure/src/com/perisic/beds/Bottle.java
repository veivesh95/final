package com.perisic.beds;

import com.perisic.util.ItemReader;

/**
 * @author Marc Conrad
 *this is a sub class for deposit item 
 */
public class Bottle extends DepositItem {
	static int weight = 50; 
	static int size = 8; 
	
	/**
	 * This is the default constructor of the Bottle class
	 * value is a variable in the super class DepositItem
	 * @throws Exception 
	 */
	public Bottle() throws Exception { 
		//value = 18;
		
	   name="bottle";
		value= ItemReader.getValueOf(name);
		
	}
}

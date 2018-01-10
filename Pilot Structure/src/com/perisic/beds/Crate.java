package com.perisic.beds;

import com.perisic.util.ItemReader;

/**
 * @author Marc Conrad
 *this is a sub class for deposit item 
 */
public class Crate extends DepositItem {
	static int weight = 500; 
	static int size = 90; 
	
	/**
	 * This is the default constructor of the Crate class
	 * Value is a variable in the super class DepositItem
	 * @throws Exception 
	 */
	public Crate() throws Exception { 
		//value = 42; 
		
		name="crate";
		value= ItemReader.getValueOf(name);
	}
}

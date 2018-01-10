package com.perisic.beds;
import com.perisic.util.*;


/**
 * @author Marc Conrad
 *This is a sub class for deposit item 
 */
public class Can extends DepositItem {
	static int weight = 40; 
	static int size = 5; 
	
	
	/**
	 * This is the default constructor of the Can class
	 * Value is a variable in the super class DepositItem
	 * @throws Exception 
	 */
	public Can() throws Exception { 
		
		name="can";
		value= ItemReader.getValueOf(name);
		
	//value = 16; 
	}
}

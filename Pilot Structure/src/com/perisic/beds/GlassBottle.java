package com.perisic.beds;

import com.perisic.util.ItemReader;

/**
 * This class is inherited from the bottle class
 */
public class GlassBottle extends Bottle  {

	/**
	 * This is the default constructor of the GlassBottle class
	 * value is a variable in the super class DepositItem
	 */
	public GlassBottle() throws Exception
	{
		name = "glassbottle";
	      //	value= 34;
		value= ItemReader.getValueOf(name);
	}
	
}

package com.perisic.beds;

import com.perisic.util.ItemReader;

/**
 * This class is inherited from the bottle class
 */
public class PlasticBottle extends Bottle {

	/**
	 * This is the default constructor of the PlasticBottle class
	 */
	public PlasticBottle()throws Exception
	{
		name="plasticbottle";
		//value= 22;
		value= ItemReader.getValueOf(name);
	}
}

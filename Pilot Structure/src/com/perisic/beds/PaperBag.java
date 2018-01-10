package com.perisic.beds;

import com.perisic.util.ItemReader;

public class PaperBag extends DepositItem {
	static int weight = 20; 
	static int size = 100; 

	public PaperBag() throws Exception {
		
	   name = "paperbag";
		//value = 20;
	   value= ItemReader.getValueOf(name);
	}

}

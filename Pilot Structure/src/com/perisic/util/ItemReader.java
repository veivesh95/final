package com.perisic.util;

import java.net.*;
import java.io.*;

public class ItemReader {
	
    public static int getValueOf(String what) throws Exception {

        URL prices = new URL("http://localhost/prices.php?item="+what);
        
        BufferedReader in = new BufferedReader( new InputStreamReader(prices.openStream()));

        String inputLine;
        int result = -2; 
        
        while ((inputLine = in.readLine()) != null) {
        	result = Integer.parseInt(inputLine); 
        	}
        in.close(); 
        return result; 
    }
    
   /* public static void main(String [] args ) { 
    	try {
			System.out.println("Can = "+getValueOf("can")); 
			System.out.println("Bottle = "+getValueOf("bottle")); 
			System.out.println("Crate = "+getValueOf("crate")); 
		} catch (Exception e) {
			System.out.println("An exception has occured: "+e.toString());
			e.printStackTrace();
		} */
    }
    

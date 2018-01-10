package com.perisic.beds;
/**
 * @author Marc Conrad
 * The ReceiptPrinter class is used to print the receipt 
 */
public class ReceiptPrinter implements PrinterInterface {
	
	/**
	 * @param str 
	 * the print method is used to print the receipt 
	 */
	public void print(String str) { 
		System.out.println(str);
	}
}

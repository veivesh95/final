package com.perisic.beds;

/**
 * The interface with the real world printer.
 * Knows, how to print stuff.
 * @author Marc Conrad
 *
 */

/**
 * This interface returns the receipt format to DepositItemReceiver class
 * 
 * @author Veivesh Krishnakumar
 *
 */
public class ReceiptPrinter implements PrinterInterface {
	/**
	 * @param str for the total sum it gets from it's associated class
	 */
	public void print(String str) {
		/**
		 * add code here to work with a real printer...
		 */
		System.out.println(str);
	}
}

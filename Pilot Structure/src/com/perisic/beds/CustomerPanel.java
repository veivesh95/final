package com.perisic.beds;

/**
 * @author Marc Conrad
 *This is a class for a customer to interact with the system
 *receiver variable of DepositItemReceiver is initiated to null
 */
public class CustomerPanel {
	DepositItemReceiver receiver = null; 
	
	/*
	 * This is the default constructor of CustomerPanel class that takes PrinterInterface as an argument)
	 */
	
	public CustomerPanel(PrinterInterface PrinterInt)
	{
		super();
		receiver = new DepositItemReceiver(PrinterInt);
	}
	
	/**
	 * @param slot
	 * Calling the classifyItem method from DepositItemReceiver class
	 * Receiver variable is used to initiate the call
	 * Implementation for this method is in the classifyItem method in DepositItemReceiver class
	 * @throws Exception 
	 */
	public void itemReceived(int slot) throws Exception { 
		receiver.classifyItem(slot); 
	}
	/**
	 * Calling the printReceipt method from DepositItemReceiver class
	 * To initiate the call receiver variable is used
	 * The return Type has been changed to String from void to make it return string value
	 * Implementation of this printReceipt method is in DepositItemReceiver class
	 */
	public String printReceipt() { 
		return receiver.printReceipt();
	}
	/**
	 * A method to check the status of the machine, also it accepts and calls the method from DepositItemReceiver class
	 * receiver variable is used to initiate the call
	 * Implementation of this checkmachine method is in DepositItemReceiver class
	 */
	
	public void checkmachine()
	{
		receiver.checkmachinestatus();
	}
	
	/**
	 * This method will clear the items from the machine
	 * This method accepts and calls the  machineClear method in DepositItemReceiver
	 * Receiver variable is used to initiate the call
	 */
	public void cleanmethod()
	{
		receiver.clearrecyclingmachine();
		
	}
	
	
	public void resetall()
	{
		receiver.resetReceiptBasis();
	}
	
	
	public boolean isReceiptBasisempty()
	{
		if(receiver.theReceiptBasis==null)
			return true;
		else
			return false;
	}
	
	
   public int getNumberofitems()
   {
	   return receiver.getnumberofitems();
   }
	
}

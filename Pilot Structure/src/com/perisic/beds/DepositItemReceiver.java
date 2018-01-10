package com.perisic.beds;

import javax.swing.JOptionPane;
/**
 * @author Marc Conrad
 * A class to carry out the overall system
 * totalweight and totalsize are two static variables to measure the weight and the size of the items
 * The variable theReceiptBasis of ReceiptBasis class is initiated to null
 * A variable called printerInt of PrinterInterface class has been set to null at the beginning
 * variable item of DepositItem class is also initiated to null to indicate that there wont be any items before adding
 */
public class DepositItemReceiver {
	
	static int totalweight = 0;
	static int totalsize = 0;
	DepositItem item = null; 
	ReceiptBasis theReceiptBasis = null; 
	ReceiptPrinter printer = new ReceiptPrinter(); 
	PrinterInterface printerInt = null;
	static int itemcount=0;
	
	/*
	 *  default constructor that takes the PrinterInterface as an argument
	 */
	public DepositItemReceiver(PrinterInterface printerInt) {
		super();
		this.printerInt = printerInt;
	}
	/**
	 * a method that creates the object to print the receipt
	 */
	public void createReceiptBasis() { 
		theReceiptBasis = new ReceiptBasis(); 
	}
	
	/**
	 * @param slot
	 * A method to maintain a slot for each items(Can, Crate,Plastic Bottle,Glass Bottle, Paper Bag)
	 * while adding each item, the total size and weight will be calculated individually
	 * If theReceiptBasis is null createReceiptBasis method will be called 
	 * This method will be called in CustomerPanel using an object of this class
	 * @throws Exception 
	 */
	public void classifyItem(int slot) throws Exception { 
		
		if( slot == 1 ) { 
			item = new Can();
			totalweight= totalweight+ Can.weight;
			totalsize=totalsize+ Can.size;
		} else if( slot == 2 ) { 
			item = new GlassBottle(); 
			totalweight= totalweight+ GlassBottle.weight;
			totalsize = totalsize + GlassBottle.size;
		} else if ( slot == 3 ) { 
			item = new PlasticBottle();
			totalweight = totalweight + PlasticBottle.weight;
			totalsize = totalsize+ PlasticBottle.size;
		} else if (slot == 4) {
			item = new PaperBag();
			totalweight = totalweight + PaperBag.weight;
			totalsize = totalsize+ PaperBag.size;
		}
		else if (slot == 5)
		{
			item= new Crate();
			totalweight = totalweight + Crate.weight;
			totalsize = totalsize+ Crate.size;
		}
		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		/**
		 * This is the method to check whether the machine is full while adding items
		 * A progress bar will be increasing as the total weight of each item increases
		 */
		if (totalweight <1600 && totalsize< 220)
		{
			RecyclingGUI.progressbar.setValue(totalweight);
			theReceiptBasis.addItem(item);
			itemcount++;
			JOptionPane.showMessageDialog(null, item.getClass().getSimpleName() + " Is Added");
			
		}
		
		else
		{
			JOptionPane.showMessageDialog(null,item.getClass().getSimpleName() + "  Cannot Be Added");
		}
	
	}
	
	/**
	 * A method which will print the receipt
	 * Return type of this method has been changed to String from void to return a string
	 * this method will be called in CustomerPanel class using an object of this class
	 */
	
	public String printReceipt() {
		
		if (theReceiptBasis==null)
		{
			createReceiptBasis();
		}
		String str = theReceiptBasis.computeSum(); 
	//	printerInt.print(str); 
		theReceiptBasis = null; 
		return str;
	}
	
	/**
	 * This indicates whether more items can be added to the machine
	 * This method will be called in CustomerPanel using an object of this class
	 */
	public void checkmachinestatus()
	{
		if (totalweight <1600 && totalsize< 220)
		{
			
			JOptionPane.showMessageDialog(null, " Could add more items");
		}
		
		else
		{
			JOptionPane.showMessageDialog(null,"  items cannot  Be Added, Machine is full");
		}
	}
	
	/**
	 * A method to call the machineclean method of the ReceiptBasis class
	 * theReceiptBasis variable of ReceiptBasis class is used to call the method
	 * implementation of machineclean method is in the ReceiptBasis
	 */
	public void clearrecyclingmachine()
	{
		
		if (theReceiptBasis==null)
		{
			createReceiptBasis();
		}
		theReceiptBasis.machineclean();
	}
	
	public void resetReceiptBasis()
	{
		theReceiptBasis=null;
	}
	
	public int getnumberofitems()
	
	{
		return itemcount;
	}
	
}

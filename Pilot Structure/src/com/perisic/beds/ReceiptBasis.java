package com.perisic.beds;
import java.util.Vector; 

/**
 * @author Marc Conrad
 * Vector class is used to indicate that the total varieties of items were not confirmed
 * variables to indicate the count of the items are initialized to 0 at the beginning 
 */
public class ReceiptBasis {
	
	int cancount,cratecount,plasBottle,glassBottle,pbagcount,totalBottCount=0;
	private Vector<DepositItem> myItems = new Vector<DepositItem>();
	 
	
	/**
	 * @param item 
	 * This method  will pass the DepositItems as a parameter and adds them one by one
	 * 1 is added to the myItems.indexOf(item) so that the count will start from 1
	 */
	
	public void addItem(DepositItem item) { 
		myItems.add(item); 
		item.number = myItems.indexOf(item)+1; 
	}
	/**
	 * This method calculates the total value of each items and the total count of each items
	 * this method will be called in printReceipt method in DepositItemReceiver class
	 * the class name is derived by using getsimplename
	 */
	
	
	public String computeSum() { 
		String receipt = ""; 
		int sum = 0; 
		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i); 
			receipt = receipt + item.number +": "+ item.getClass().getSimpleName() +": "+item.value; 
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value; 
		//	totalBottCount= glassBottle+plasBottle;
			
			if(item.name== "can")
			{
				cancount++;
			}
			
			else if (item.name=="glassbottle")
			{
				glassBottle++;
			}
			
			else if (item.name=="plasticbottle")
			{
				plasBottle++;
			}
			else if (item.name=="crate") 
			{
				cratecount++;
			}
			
			else if(item.name=="paperbag")
			{
				pbagcount++;
			}
			
			
			else if(item.name=="bottle")
			{
				
				totalBottCount++;
			}
		}
		receipt = receipt + "Total: "+sum; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Total number of Cans :"+ cancount;
		receipt = receipt + System.getProperty("line.separator");
        receipt = receipt + "Total number of Glass Bottles : " + glassBottle;
        receipt = receipt + System.getProperty("line.separator");
        receipt = receipt + "Total number of Plastic Bottles : " + plasBottle;
        receipt = receipt + System.getProperty("line.separator");
        receipt = receipt + "Total number of Crates : " + cratecount;
        receipt = receipt + System.getProperty("line.separator");
        receipt = receipt + "Total number of Paper Bags : " + pbagcount;
        receipt = receipt + System.getProperty("line.separator");
        totalBottCount= glassBottle+plasBottle;
        receipt = receipt + "Total bottle count =" + totalBottCount;
		
		return receipt; 
	}
	
	/**
	 * This method will clear all the items in the recycling machine
	 * once it is cleared progress bar will be set to 0
	 * once it is cleared totalweight and totalsize will be set to 0
	 * Since the progress bar uses the total weight it should be set to total weight 
	 * This method will be called in DepositItemReceiver class using an object of this class
	 */
	public void machineclean()
	{
		myItems.clear();
		RecyclingGUI.progressbar.setValue(0);
		DepositItemReceiver.totalweight=0;
		DepositItemReceiver.totalsize=0;
		RecyclingGUI.progressbar.setValue(DepositItemReceiver.totalweight);
	}
}

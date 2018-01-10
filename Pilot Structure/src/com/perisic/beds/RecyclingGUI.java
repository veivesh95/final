package com.perisic.beds;


import com.perisic.util.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.apache.xmlrpc.WebServer;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A Simple Graphical User Interface for the Recycling Machine.
 * @author Marc Conrad
 *An object of customer panel is created passing the ReceiptPrinter as parameter
 */
public class RecyclingGUI extends JFrame implements ActionListener  {
	
	 static CustomerPanel myCustomerPanel  = new CustomerPanel(new ReceiptPrinter());
	 static int secondpassed = 0;

		static Timer mytimer=new Timer();
		static TimerTask task = new  TimerTask(){
			public void run()
			{
				secondpassed ++;
				if(secondpassed > 30)
				{
					if(!myCustomerPanel.isReceiptBasisempty())
					{
						JOptionPane.showMessageDialog(null,"Session time out");
					}
					secondpassed=0;
					myCustomerPanel.resetall();					
					
					
				}
			}
		};
		/**
		 * The method to start the thread
		 */
		public static void start()
		{
			mytimer.scheduleAtFixedRate(task,1000,1000 );
		}
		
	
	
	

	/**
	 * This method performs the corresponding events when the buttons are fired
	 * itemReceived method is called here via myCustomerPanel in order to add items to their allocated slots
	 * checkMachine method is called here via  myCustomerPanel whether we can add more items into the machine
	 * printReceipt method is called here via myCustomerPanel to print the receipt for the added items
	 * cleanmethod method is called here via myCustomerPanel so that the items will be removed from the machine
	 */
	public void actionPerformed(ActionEvent e) {
		
		
		
		/*System.out.println("Received: e.getActionCommand()="+e.getActionCommand()+
							" e.getSource()="+e.getSource().toString() );*/ 
		
		if( e.getSource().equals(slot1) ) { 
            try {
				myCustomerPanel.itemReceived(1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           } 
		
		else if (e.getSource().equals(slot2))
		{
			try {
				myCustomerPanel.itemReceived(2);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource().equals(slot3))
		{
			try {
				myCustomerPanel.itemReceived(3);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource().equals(slot4))
		{
			try {
				myCustomerPanel.itemReceived(4);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource().equals(slot5))
		{
			try {
				myCustomerPanel.itemReceived(5);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource().equals(receipt))
		{
			JOptionPane.showMessageDialog(null, myCustomerPanel.printReceipt());
		}
		
		else if (e.getSource().equals(chkmachine))
		{
			myCustomerPanel.checkmachine();
		}
		
		else if (e.getSource().equals(clearmachine))
		{
			myCustomerPanel.cleanmethod();
		}
		
		else if (e.getSource().equals(login))
		{
			String message; 
			message = JOptionPane.showInputDialog("Type the password please");
			if(message.equals("divvya"))
			{
				clearmachine.setEnabled(true);
				logout.setEnabled(true);
			}
			else 
				JOptionPane.showMessageDialog(null, "Incorrect Password");
		}
		else if (e.getSource().equals(logout))
		{
			clearmachine.setEnabled(false);
			logout.setEnabled(false);
		}




	}
	
	JButton slot1 = new JButton("Cans"); 
	JButton slot2 = new JButton("Glass Bottles"); 
	JButton slot3 = new JButton("Plastic Bottles"); 
	JButton slot4 = new JButton("Paper Bags"); 
	JButton slot5 = new JButton("Crates"); 
	JButton chkmachine = new JButton("Check Machine");
	JButton clearmachine = new JButton("Clear Machine");
	JButton login = new JButton("Login");
	JButton logout = new JButton("Logout");
	
	JButton receipt = new JButton("Receipt"); 
	
	public static JProgressBar progressbar=new JProgressBar(0,725);
	
	/*
	 * This method outputs the GUI
	 */
	public RecyclingGUI() {
		super();
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel panel = new JPanel(); 
		panel.add(slot1); 
		panel.add(slot2);
		panel.add(slot3); 
		panel.add(slot4); 
		panel.add(slot5); 
		panel.add(clearmachine);
		panel.add(progressbar);
		panel.add(chkmachine);
		panel.add(login);
		panel.add(logout);
		
		/*
		 * calling actionlistener
		 */
		slot1.addActionListener(this); 
		slot2.addActionListener(this); 
		slot3.addActionListener(this); 
		slot4.addActionListener(this); 
		slot5.addActionListener(this); 
		chkmachine.addActionListener(this);
		clearmachine.addActionListener(this);
		login.addActionListener(this);
		logout.addActionListener(this);
		
		panel.add(receipt); 
		receipt.addActionListener(this); 
		
		clearmachine.setEnabled(false);
		logout.setEnabled(false);
		
		getContentPane().add(panel);
		panel.repaint();
	
	}
	
	/*
	 * main method
	 */
	public static void main(String [] args ) { 
		RecyclingGUI myGUI = new RecyclingGUI(); 
		myGUI.setVisible(true); 
		RecyclingGUI.start();
		
		
		
		 try {
			   System.out.println("Starting the Recycling Machine Remote Service."); 
			   WebServer server = new WebServer(88);
			   RecyclingService theService = new RecyclingService(); 
			   theService.setCustomerPanel(RecyclingGUI.myCustomerPanel);
			   server.addHandler("recyclingMachine", theService);
			   server.start();
			  } catch (Exception exception) {
			   System.err.println("JavaServer: " + exception);
			   }
		

	}

}

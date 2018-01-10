package com.perisic.util;

import com.perisic.beds.CustomerPanel;
public class RecyclingService {
	
CustomerPanel myRecyclingMachine = null; 
	
	public void setCustomerPanel(CustomerPanel myPanel) { 
		myRecyclingMachine = myPanel; 
	}
	private String sessionCookie = "NotSet"+Math.random(); 
	
	public int getNumberOfItems(String mySessionCookie) { 
		// We have to implement something here. For the moment, just assume there will be four items in the machine. 
		if(mySessionCookie.equals(sessionCookie)) { 
			return myRecyclingMachine.getNumberofitems(); 
		} else { 
			return -1; 
		}
	}
	
	public String testConnection() { 
		return "The connection is fine! Thank you!"; 
	}
	
	public String login(String password) { 
		if(password.equals("123")) { // Needs some more sophisticated mechanism such encryption and database etc etc!!!!
			sessionCookie = "Cookie"+Math.random(); 
			return sessionCookie; 
		} else { 
			return "not ok"; // Let's come to this point later. 
		}
	}
	
	public String logout() { 
		sessionCookie = "NotSet"+Math.random(); 
		return "logout ok"; 
	}
}




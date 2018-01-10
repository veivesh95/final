
/**
 * Represents a Can that is inserted into the recycling machine.
 */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * This entity class returns the weight, size and value for a can. Extends
 * DepositItem class
 * 
 * @author Veivesh Krishnakumar
 *
 */
public class Can extends DepositItem {
	static int weight = 4;
	static int size = 5;

	/**
	 * Method returns the value of a can
	 */
	public Can() {
		hqInterface hqif;
		try {
			name = "Can";
			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);

			System.setSecurityManager(new RMISecurityManager());
			hqif = (hqInterface) Naming.lookup("rmi://localhost:6002/rmiServerKey");
			value = hqif.valueProvider(name);
		}

		catch (Exception ex) {
			System.out.println("Can error: " + ex);
		}
	}
}
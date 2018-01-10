

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Vector;

/**
 * Developed on Week 2 This entity class returns the weight, size and value for
 * a paperbag. Extends DepositItem class
 * @author Veivesh Krishnakumar
 */
public class Paperbag extends DepositItem {

	static int weight = 2;
	static int size = 10;

	/**
	 * Returns the value of a paperbag (15) and the name of the item
	 */
	public Paperbag() {
		hqInterface hqif;
		try {
			name = "Paper_Bag";
			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);

			System.setSecurityManager(new RMISecurityManager());
			hqif = (hqInterface) Naming.lookup("rmi://localhost:6002/rmiServerKey");
			value = hqif.valueProvider(name);
		}

		catch (Exception ex) {
			System.out.println("Paper_Bag error: " + ex);
		}
	}

}

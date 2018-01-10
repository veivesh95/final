

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Vector;

/**
 * This class extends non-abstract class Bottle.java
 * @author Veivesh Krishnakumar
 *
 */
public class Plastic_Bottle extends Bottle {
	static int size = 10;
	static int weight = 15;

	/**
	 * Returns the name of the item and the value of it
	 */
	public Plastic_Bottle() {

		hqInterface hqif;
		try {
			name = "Plastic_Bottle";
			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);

			System.setSecurityManager(new RMISecurityManager());
			hqif = (hqInterface) Naming.lookup("rmi://localhost:6002/rmiServerKey");
			value = hqif.valueProvider(name);
		}

		catch (Exception ex) {
			System.out.println("Plastic_Bottle error: " + ex);
		}
	}
}

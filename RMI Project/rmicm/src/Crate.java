import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Vector;

/**
 * This entity class returns the weight, size and value for a crate. Extends
 * DepositItem class
 * 
 * @author Veivesh
 *
 */
public class Crate extends DepositItem {
	static int weight = 15;
	static int size = 90;

	/**
	 * Method returns the value of a crate
	 */
	public Crate() {
		hqInterface hqif;
		try {
			name = "Crate";
			Vector<String> itemNameVector = new Vector<String>();
			itemNameVector.add(name);

			System.setSecurityManager(new RMISecurityManager());
			hqif = (hqInterface) Naming.lookup("rmi://localhost:6002/rmiServerKey");
			value = hqif.valueProvider(name);
		}

		catch (Exception ex) {
			System.out.println("Crate error: " + ex);
		}
	}
}

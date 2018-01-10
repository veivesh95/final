import java.rmi.*;

/**
 * Service Provider method calls
 * @author Veivesh Krishnakumar
 *
 */
public interface hqInterface extends Remote {
	public int valueProvider(String itemName) throws RemoteException;

	public String getConnection() throws RemoteException;

	public void logout() throws RemoteException;

	public String addItem(String itemName, int itemValue, String clientIP) throws RemoteException;
}

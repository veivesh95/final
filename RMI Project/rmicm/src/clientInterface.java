import java.rmi.*;

/**
 * Recycling Service Method calls (from client machine)
 * @author Veivesh Krishnakumar
 *
 */
public interface clientInterface extends Remote {
	public int getNumberOfItems(String mySessionCookie) throws RemoteException;

	public String reset() throws RemoteException;

	public String testConnection() throws RemoteException;

	public String login(String password) throws RemoteException;

	public String logout() throws RemoteException;

}

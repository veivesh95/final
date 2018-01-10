

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;
import java.awt.GridLayout;
import java.awt.Font;

/**
 * This is the gui class of Engineer's account in a 
 * client machine
 * @author Veivesh Krishnakumar
 *
 */
public class EngineerOptions extends JFrame implements ActionListener {

	private static String fileName = null;
	private static String filePath = null;
	static CustomerPanel myCustomerPanel = new CustomerPanel(new ReceiptPrinter());
	private final JButton btnReset = new JButton("Reset Machine");
	private final JButton btnLogout = new JButton("Sign out");
	private final JButton btnShowSummary = new JButton("Show Summary");
	private final JButton btnSafeMode = new JButton("Log out from Server");
	/**
	 * main method just renders the gui elements
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EngineerOptions engOp = new EngineerOptions();
		engOp.setVisible(true);
	}

	/**
	 * default const of the class
	 * gui elements and props are mentioned here
	 */
	public EngineerOptions() {
		super("Engineer Options");
		setSize(300, 120);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(btnReset);
		btnReset.addActionListener(this);

		btnShowSummary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(btnShowSummary);
		btnShowSummary.addActionListener(this);

		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(btnLogout);
		btnLogout.addActionListener(this);
		
		btnSafeMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnSafeMode);
		btnSafeMode.addActionListener(this);

		getContentPane().add(panel);
	}

	/**
	 * ActionEvent listener method. event driven stuff
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnReset)) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResponse = JOptionPane.showConfirmDialog(this,
					"Do you really want to clear the machine? \n(This will reset all the item count stored in the system)",
					"Caution!", dialogButton);
			if (dialogResponse == JOptionPane.YES_OPTION) {
				try {
					myCustomerPanel.clearReceipt();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			} else {
				System.out.println("JOption Message closed");
			}
		}

		if (event.getSource().equals(btnLogout)) {
			this.dispose();
		}

		if (event.getSource().equals(btnShowSummary)) {
			openReceipt();
		}
		
		/*if(event.getSource().equals(btnSafeMode)) {
			RecyclingGUI rg = new RecyclingGUI();
			hqInterface hqif;
			try {
				hqif.logout();
				rg.connectToServer = new XmlRpcClient("http://localhost/RPC2");
				try {
					rg.cookie = RecyclingGUI.connectToServer.execute("serverHandler.logout", new Vector());
					System.out.println("Logged out from Server!");
					rg.btnFalse();
				} catch (XmlRpcException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}

	/**
	 * This method is used to open the receipt under today's name so it can be viewed 
	 * Straightly from his GUI without checking it in local directory
	 */
	public void openReceipt() {
		this.fileName = ReceiptBasis.getDate();
		this.filePath = ReceiptBasis.filePath;
		System.out.println(filePath);
		System.out.println(fileName);
		ProcessBuilder pb = new ProcessBuilder("notepad.exe", filePath + "\\receipt\\" + fileName + ".txt");
		try {
			pb.start();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No receipts printed today");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No receipts printed today");
		}
	}

}

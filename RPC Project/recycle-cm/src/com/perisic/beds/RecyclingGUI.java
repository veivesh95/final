package com.perisic.beds;

import com.perisic.util.RecyclingService;
import org.apache.xmlrpc.WebServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.xmlrpc.*;
import java.util.Vector;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

/**
 * A Simple Graphical User Interface for the Recycling Machine which acts as the
 * client side application, and also a specified portal to the Engineers who
 * check this app.
 *
 * @author Marc Conrad
 */
public class RecyclingGUI extends JFrame implements ActionListener {

	public static XmlRpcClient connectToServer;
	public static Object cookie;
	static int timer = 0;
	public static String sessionCookie = null;
	public static String clientIP = null;
	public static Vector allSelectedItems = new Vector();
	public ReceiptBasis myReceiptBasis = new ReceiptBasis();
	static CustomerPanel myCustomerPanel = new CustomerPanel(
			new ReceiptPrinter());

	private int bottleCount = 0;
	private int paperBagCount = 0;
	private int crateCount = 0;
	private int canCount = 0;

	JButton btnCan = new JButton("Can");
	JButton btnCrate = new JButton("Crate");
	JButton btnPaperBag = new JButton("Paperbag");
	JButton btnGlassBottle = new JButton("Glass bottle");
	JButton btnPlasticBottle = new JButton("Plastic bottle");
	JButton btnReceipt = new JButton("Receipt");
	JButton btnLogin = new JButton("Login");

	public static JProgressBar progressBar = new JProgressBar(0, 1000);
	private static final JTextArea itemReceiverTextArea = new JTextArea();

	static Timer SysTimer = new Timer();

	/**
	 * Thread which is created to specify the task of of checking whether the
	 * client goes to idle state or not, incase if that happens what actions
	 * should be performed.
	 */
	static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			timer += 1;

			if (timer > 30) {
				if (!myCustomerPanel.machineHasItems()) {
					JOptionPane.showMessageDialog(null, "Idle Time Out.");
				}
				timer = 0; // timer reset
				myCustomerPanel.resetMachine();
			}
		}
	};

	/**
	 * Specifying delay time, duration span for the thread which holds the
	 * properties to check RecycleGUI idle time
	 */
	public static void startTimer() {
		SysTimer.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * Main method of Recycling GUI/ client side application which renders the
	 * GUI and hosting Server for Headquater's access and creates the pathway to
	 * connect to the Headquarter's services
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RecyclingGUI myGUI = new RecyclingGUI();
		myGUI.setVisible(true);
		startTimer();
		try {
			InetAddress IP = InetAddress.getLocalHost();
			clientIP = IP.getHostAddress();
			System.out.println("IP of my system is " + clientIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Server is getting ready!");
			WebServer server = new WebServer(5001);
			RecyclingService theService = new RecyclingService();
			theService.setCustomerPanel(RecyclingGUI.myCustomerPanel);
			server.addHandler("clientHandler", new RecyclingService());
			server.start();
			System.out
					.println("Server is up and running! \nListening for clients!");
		} catch (Exception exception) {
			System.err.println("Server Error: " + exception + "\n");
		}

		try {
			connectToServer = new XmlRpcClient("http://localhost/RPC2");
			cookie = connectToServer.execute("serverHandler.getConnection",
					new Vector());
			System.out.println(cookie.toString());

			myGUI.btnTrue();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Method which holds the gui elements and it's properties that needs to be
	 * rendered during the main method call
	 */
	public RecyclingGUI() {
		super("Recycle IO");
		// setLocationRelativeTo(null);
		setSize(580, 472);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();

		getContentPane().add(panel, BorderLayout.EAST);

		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnReceipt.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnReceipt.setForeground(Color.WHITE);
		btnReceipt.setBackground(Color.DARK_GRAY);
		// clearBtn.addActionListener(this);
		btnReceipt.addActionListener(this);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel lblNewLabel = new JLabel("");
		panel_3.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\can1.PNG"));
		panel_3.add(btnCan);

		btnCan.addActionListener(this);

		btnCan.setEnabled(false);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel lblNewLabel_1 = new JLabel("");
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\crate1.PNG"));
		panel_4.add(btnCrate);
		btnCrate.addActionListener(this);
		btnCrate.setEnabled(false);

		JLabel label = new JLabel("");
		panel_4.add(label);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel lblNewLabel_4 = new JLabel("");
		panel_7.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\plastic1.PNG"));
		panel_7.add(btnPlasticBottle);
		btnPlasticBottle.addActionListener(this);
		btnPlasticBottle.setEnabled(false);

		JLabel label_3 = new JLabel("");
		panel_7.add(label_3);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel lblNewLabel_3 = new JLabel("");
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\glass1.PNG"));
		panel_6.add(btnGlassBottle);
		btnGlassBottle.addActionListener(this);
		btnGlassBottle.setEnabled(false);

		JLabel label_2 = new JLabel("");
		panel_6.add(label_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel lblNewLabel_2 = new JLabel("");
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\paperBag1.PNG"));
		panel_5.add(btnPaperBag);
		btnPaperBag.addActionListener(this);
		btnPaperBag.setEnabled(false);

		JLabel label_1 = new JLabel("");
		panel_5.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.addActionListener(this);
		btnLogin.setEnabled(true);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap(416, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING, false)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										panel_1,
																										GroupLayout.PREFERRED_SIZE,
																										502,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										btnLogin))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										btnPanel,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										itemReceiverTextArea,
																										GroupLayout.PREFERRED_SIZE,
																										196,
																										GroupLayout.PREFERRED_SIZE)))
																.addContainerGap())
												.addComponent(
														panel_2,
														GroupLayout.PREFERRED_SIZE,
														564,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(51)
																.addComponent(
																		progressBar,
																		GroupLayout.PREFERRED_SIZE,
																		286,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		btnReceipt,
																		GroupLayout.PREFERRED_SIZE,
																		92,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(51)))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(11)
																.addComponent(
																		btnLogin,
																		GroupLayout.PREFERRED_SIZE,
																		31,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														panel_1,
														GroupLayout.PREFERRED_SIZE,
														58,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING, false)
												.addComponent(
														itemReceiverTextArea)
												.addComponent(
														btnPanel,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED,
										70, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnReceipt)
																.addGap(13))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		progressBar,
																		GroupLayout.PREFERRED_SIZE,
																		43,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)))
								.addComponent(panel_2,
										GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE)));

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\assets\\waste2.PNG"));
		panel_1.add(lblNewLabel_5);
		itemReceiverTextArea
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		itemReceiverTextArea.setForeground(Color.WHITE);
		itemReceiverTextArea.setBackground(new Color(0, 128, 0));
		btnReceipt.setEnabled(false);

		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel
				.setHorizontalGroup(gl_btnPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_btnPanel
										.createSequentialGroup()
										.addGroup(
												gl_btnPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_btnPanel
																		.createSequentialGroup()
																		.addGap(54)
																		.addComponent(
																				panel_5,
																				GroupLayout.PREFERRED_SIZE,
																				185,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_btnPanel
																		.createSequentialGroup()
																		.addGap(5)
																		.addGroup(
																				gl_btnPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_btnPanel
																										.createSequentialGroup()
																										.addComponent(
																												panel_3,
																												GroupLayout.PREFERRED_SIZE,
																												145,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												panel_4,
																												GroupLayout.PREFERRED_SIZE,
																												150,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_btnPanel
																										.createSequentialGroup()
																										.addComponent(
																												panel_7,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												panel_6,
																												GroupLayout.PREFERRED_SIZE,
																												150,
																												GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		gl_btnPanel
				.setVerticalGroup(gl_btnPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_btnPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_btnPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panel_4,
																GroupLayout.PREFERRED_SIZE,
																69,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																panel_3,
																GroupLayout.PREFERRED_SIZE,
																69,
																GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addGroup(
												gl_btnPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panel_6,
																GroupLayout.DEFAULT_SIZE,
																76,
																Short.MAX_VALUE)
														.addComponent(
																panel_7,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(panel_5,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		btnPanel.setLayout(gl_btnPanel);

		GroupLayout gl_panel1 = new GroupLayout(panel);
		gl_panel1
				.setHorizontalGroup(gl_panel1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel1
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnPanel,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(
												gl_panel1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel1
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				progressBar,
																				GroupLayout.DEFAULT_SIZE,
																				286,
																				Short.MAX_VALUE))
														.addGroup(
																gl_panel1
																		.createSequentialGroup()
																		.addGap(124)
																		.addGroup(
																				gl_panel1
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								itemReceiverTextArea,
																								GroupLayout.PREFERRED_SIZE,
																								309,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_panel1
																										.createSequentialGroup()
																										.addComponent(
																												btnReceipt,
																												GroupLayout.PREFERRED_SIZE,
																												150,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(111)
																										.addComponent(
																												btnLogin)))))
										.addContainerGap()));
		gl_panel1
				.setVerticalGroup(gl_panel1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel1
										.createSequentialGroup()
										.addGap(26)
										.addGroup(
												gl_panel1
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																btnPanel,
																GroupLayout.PREFERRED_SIZE,
																294,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panel1
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel1
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnLogin,
																								GroupLayout.PREFERRED_SIZE,
																								39,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnReceipt,
																								GroupLayout.PREFERRED_SIZE,
																								40,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addComponent(
																				itemReceiverTextArea,
																				GroupLayout.PREFERRED_SIZE,
																				174,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				progressBar,
																				GroupLayout.PREFERRED_SIZE,
																				29,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(47)));
		progressBar.setBackground(Color.LIGHT_GRAY);

		btnFalse();

		panel.setLayout(gl_panel);

		panel.repaint();
	}

	/**
	 * Action event listeners written to give specific task to elements event
	 * listeners.
	 * 
	 * @param e
	 *            acts as the instance that the specified element has passed an
	 *            event/action
	 */
	public void actionPerformed(ActionEvent e) {
		timer = 0;

		try {
			if (e.getSource().equals(btnCan)) {
				canCount++;
				setValues();
				myCustomerPanel.itemReceived(1);
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnCrate)) {
				crateCount++;
				setValues();
				myCustomerPanel.itemReceived(2);
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnPaperBag)) {
				paperBagCount++;
				setValues();
				myCustomerPanel.itemReceived(3);
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnGlassBottle)) {
				bottleCount++;
				setValues();
				myCustomerPanel.itemReceived(4);
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnPlasticBottle)) {
				bottleCount++;
				setValues();
				myCustomerPanel.itemReceived(5);
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnReceipt)) {
				JOptionPane.showMessageDialog(null,
						myCustomerPanel.guiReceipt());
				itemReceiverTextArea.setText("");
				// Text that holds the receipt information
				System.out.println("Received: " + e.getActionCommand());
			}

			else if (e.getSource().equals(btnLogin)) {

				String password;
				password = JOptionPane
						.showInputDialog("Enter Engineer password to login: ");
				if (password.equals("123")) { // clear.setEnabled(true); //
												// logout.setEnabled(true);
					EngineerOptions eng = new EngineerOptions();
					eng.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null,
							"Error: Check your credentials again.");

			}

		} catch (NullPointerException ex2) {
			System.out.println(ex2);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "" + ex);
		}
	}

	/**
	 * Method that holds temporarily the choices/inputs received from the client
	 * and display them in main Gui's textArea element
	 */
	private void setValues() {

		itemReceiverTextArea.setText("");
		itemReceiverTextArea.append("--------------------------------"
				+ System.getProperty("line.separator"));

		if (canCount > 0) {
			itemReceiverTextArea.append("Number of Cans: " + canCount
					+ System.getProperty("line.separator"));
		}
		if (bottleCount > 0) {
			itemReceiverTextArea.append("Number of Bottles: " + bottleCount
					+ System.getProperty("line.separator"));
		}
		if (crateCount > 0) {
			itemReceiverTextArea.append("Number of Crate: " + crateCount
					+ System.getProperty("line.separator"));
		}
		if (paperBagCount > 0) {
			itemReceiverTextArea.append("Number of PaperBag: " + paperBagCount
					+ System.getProperty("line.separator"));
		}

		itemReceiverTextArea.append(" --------------------------------");
	}

	/**
	 * Method which changes all the button's state to Enabled when the Recycle
	 * GUI connects to the Headquarter's server
	 */
	public void btnTrue() {
		btnCan.setEnabled(true);
		btnCrate.setEnabled(true);
		btnPaperBag.setEnabled(true);
		btnGlassBottle.setEnabled(true);
		btnPlasticBottle.setEnabled(true);
		btnReceipt.setEnabled(true);
		btnLogin.setEnabled(true);
	}

	/**
	 * Method that invokes all the buttons to disabled state when signing out
	 * from the main server
	 */
	public void btnFalse() {
		btnCan.setEnabled(false);
		btnCrate.setEnabled(false);
		btnPaperBag.setEnabled(false);
		btnGlassBottle.setEnabled(false);
		btnPlasticBottle.setEnabled(false);
		btnReceipt.setEnabled(false);
		btnLogin.setEnabled(false);
	}

}

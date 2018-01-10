package com.perisic.beds.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
//import java.util.concurrent.CountedCompleter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.xmlrpc.*;
import java.net.InetAddress;

/**
 * A Simple Graphical User Interface for the Recycling Machine which runs at the
 * headquarters and acts as the main server for all the clients which connects
 * through.
 *
 * @author Marc Conrad
 */
public class ClientGUI extends JFrame implements ActionListener {

	private String mySessionCookie = "not set";
	private Vector params = new Vector();
	private String serverIP = null;
	private static String clientCount;
	static int timer = 0;

	JButton loginBtn = new JButton("Login");
	JButton logoutBtn = new JButton("Logout");
	JButton itemBtn = new JButton("# Items");
	JButton testBtn = new JButton("Test");
	JButton applyBtn = new JButton("Apply");
	JButton itemSummary = new JButton("Machine Summary");
	static final String items[] = { "Choose...", "Can", "Crate",
			"Glass_Bottle", "Plastic_Bottle", "Paper_Bag" };
	JComboBox itemPicker = new JComboBox(items);
	JTextField textBox = new JTextField(10);
	final private static JLabel clientCountLabel = new JLabel();

	/**
	 * Default constructor of the CLientGUI class
	 */
	public ClientGUI() {
	}

	/**
	 * oveloaded CLientGUI constructor to pass the GUI properties including the
	 * JFrame title as the paramater.
	 * 
	 * @param appName
	 *            - JFrame title name
	 */
	public ClientGUI(String appName) {
		super(appName);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
				- this.getSize().height / 2);

		parseClientCount(ServiceProvider.numberOfClients);
	}

	/**
	 * A simple non returning method to change the Integer values to String so
	 * it can be printed in the Java Message Dialog.
	 * 
	 * @param number
	 *            - the parameter which has to be parsed as the String value
	 */
	private static void parseClientCount(int number) {
		clientCount = Integer.toString(number);
	}

	static Timer SysTimer = new Timer();

	/**
	 * Thread which is created to specify the task of changing the
	 * clientCountLabel to be updated for each timespan it passes
	 */
	static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			parseClientCount(ServiceProvider.numberOfClients);
			clientCountLabel.setText("  Number of Clients: " + clientCount);
		}
	};

	/**
	 * 
	 */
	public static void startTimer() {
		SysTimer.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * Method which holds the properties and elements of the ClientGUI
	 * application with it's positioning and other GUI related codes.
	 * 
	 * @param pane
	 *            to refer Java container gui pane to add all the properties and
	 *            elements within the container.
	 */
	public void addComponentsToPane(final Container pane) {
		try {
			InetAddress IP = InetAddress.getLocalHost();
			this.serverIP = IP.getHostAddress();
			System.out.println("IP of my system is " + serverIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		JPanel compsToLayout = new JPanel();
		// compsToLayout.setLayout(layout);

		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(2, 2));

		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(1, 3));

		JButton b = new JButton("Just fake button");
		Dimension buttonSize = b.getPreferredSize();
		compsToLayout.setPreferredSize(new Dimension((int) (buttonSize
				.getWidth() * 2.5) + 25,
				(int) (buttonSize.getHeight() * 3.5) + 25 * 2));

		compsToLayout.add(loginBtn);
		compsToLayout.add(logoutBtn);
//		compsToLayout.add(itemBtn);
		compsToLayout.add(testBtn);
		compsToLayout.add(applyBtn);
		compsToLayout.add(itemSummary);

		logoutBtn.setEnabled(false);
		applyBtn.setEnabled(false);
		itemPicker.setEnabled(false);
		textBox.setEnabled(false);
		itemBtn.setEnabled(false);

		loginBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		itemBtn.addActionListener(this);
		testBtn.addActionListener(this);
		applyBtn.addActionListener(this);
		itemSummary.addActionListener(this);

		compsToLayout.add(itemPicker);
		compsToLayout.add(textBox);
		textBox.setText("Change item value");
		// compsToLayout.add(new Label("Change item value?"));
		compsToLayout.add(applyBtn);

		itemPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBoxValueChange();
			}
		});

		controls.add(new Label("Welcome to the HQ |"));
		controls.add(new Label("HQ's IP is " + serverIP + " |"));
		controls.add(clientCountLabel);

		pane.add(compsToLayout, BorderLayout.NORTH);
		pane.add(new JSeparator(), BorderLayout.CENTER);
		// pane.add(comboPanel, BorderLayout.NORTH);
		// pane.add(new JSeparator(), BorderLayout.CENTER);
		pane.add(controls, BorderLayout.SOUTH);
	}

	/**
	 * Event driven method to get the value of each item to be passed in the
	 * JText input field so the user can see the results before they want to
	 * change it.
	 */
	private void textBoxValueChange() {
		ValueProvider val = new ValueProvider();
		String choice = itemPicker.getSelectedItem().toString();
		int tempValue = val.getValue(choice);
		textBox.setText(Integer.toString(tempValue));
	}

	/**
	 * Method which extracts the elements filled container to pass on parent
	 * JFrame container to start the gui when the main class run.
	 */
	private static void createAndShowGUI() {
		ClientGUI frame = new ClientGUI("Recycling Center");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
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

		if (e.getSource().equals(loginBtn)) {
			try {
				String message;
				message = JOptionPane
						.showInputDialog("Type the password please");
				params.add(message);
				XmlRpcClient server = new XmlRpcClient(
						"http://localhost:5001/RPC2");
				Object result = server.execute("clientHandler.login", params);
				// System.out.println("Result is: "+result);
				if (result.equals("not ok")) {
					System.out.println("Login unsuccessful");
					mySessionCookie = "some random string here";
				} else {
					System.out
							.println("Thank you. Login successful. You can now work with the recycling machine. Have a nice day!");
					mySessionCookie = result.toString();

					logoutBtn.setEnabled(true);
					applyBtn.setEnabled(true);
					itemPicker.setEnabled(true);
					textBox.setEnabled(true);
					itemBtn.setEnabled(true);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //

		} else if (e.getSource().equals(itemBtn)) {
			try {
				XmlRpcClient server = new XmlRpcClient(
						"http://localhost:5001/RPC2");
				Vector test = new Vector<>();
				test.add(mySessionCookie);
				Object result = server.execute(
						"clientHandler.getNumberOfItems", test);
				if (result.toString().equals("-1")) {
					System.out.println("Please login to access service.");
				} else {
					System.out.println("There are " + result.toString()
							+ " items in the machine.");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"Start a client machine before running the test");
			} //

		} else if (e.getSource().equals(logoutBtn)) {
			try {
				XmlRpcClient server = new XmlRpcClient(
						"http://localhost:5001/RPC2");
				Object result = server.execute("clientHandler.logout",
						new Vector());
				System.out.println("Result from logout process: "
						+ result.toString());

				logoutBtn.setEnabled(false);
				itemBtn.setEnabled(false);
				applyBtn.setEnabled(false);
				itemPicker.setEnabled(false);
				textBox.setEnabled(false);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //

		} else if (e.getSource().equals(testBtn)) {
			try {
				System.out.println(ServiceProvider.depositedItems.size());
				// textBoxValueChange();
				XmlRpcClient server = new XmlRpcClient(
						"http://localhost:5001/RPC2");
				Object result = server.execute("clientHandler.testConnection",
						new Vector());
				System.out.println("Result from test: " + result.toString());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"Start a client machine before running the test");
			} //
		} else if (e.getSource().equals(applyBtn)) {
			// textBoxValueChange();
			String itemName = itemPicker.getSelectedItem().toString();
			int newValue = Integer.parseInt(textBox.getText());
			new ValueProvider().setValue(itemName, newValue);
			JOptionPane.showMessageDialog(null, "New value has been updated");
			System.out.println("Value of " + itemName + " has been changed to "
					+ newValue + ".");
			// textBoxValueChange();
		} else if (e.getSource().equals(itemSummary)) {
			System.out.println(ServiceProvider.depositedItems.size());
			Vector<String> columnNames = new Vector<String>();
			columnNames.addElement("Number");
			columnNames.addElement("Date & Time");
			columnNames.addElement("Name");
			columnNames.addElement("Value");
			columnNames.addElement("Machine ID");

			int row = 0;

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			DefaultTableModel tbl = new DefaultTableModel(columnNames, row);

			for (int i = 0; i < ServiceProvider.depositedItems.size(); i++) {
				String[] getValue;
				getValue = (String[]) ServiceProvider.depositedItems.get(i);

				// DepositItem deposit = ServiceProvider.depositedItems.get(i);
				tbl.addRow(new Object[] { getValue[0], getValue[1],
						getValue[2], getValue[3], getValue[4] });
			}

			JTable table = new JTable(tbl);

			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane, BorderLayout.CENTER);
			frame.setLocationRelativeTo(null);
			frame.setSize(750, 150);
			frame.setVisible(true);

		}
	}

	/**
	 * Main method which creates the GUI rendered through the methods
	 * createAndShowGUI, start the WebServer so the Headquuarters could be able
	 * to access information and specifies a path to access the headquarters and
	 * the methods it offers to the clients
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		startTimer();

		try {
			WebServer server = new WebServer(80);
			server.addHandler("serverHandler", new ServiceProvider());
			server.start();
			System.out.println("Server is up and running!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

}

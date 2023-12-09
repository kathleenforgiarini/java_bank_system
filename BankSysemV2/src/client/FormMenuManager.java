package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class FormMenuManager {

	private JFrame frmHomeManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuManager window = new FormMenuManager();
					window.frmHomeManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeManager = new JFrame();
		frmHomeManager.setTitle("Home Manager");
		frmHomeManager.setBounds(100, 100, 450, 300);
		frmHomeManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeManager.getContentPane().setLayout(null);
		
		JButton btnAddCustomer = new JButton("Add New Customer");
		btnAddCustomer.setBounds(145, 53, 129, 21);
		frmHomeManager.getContentPane().add(btnAddCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.setBounds(145, 84, 129, 21);
		frmHomeManager.getContentPane().add(btnRemoveCustomer);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.setBounds(145, 115, 129, 21);
		frmHomeManager.getContentPane().add(btnOpenAccount);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.setBounds(145, 146, 129, 21);
		frmHomeManager.getContentPane().add(btnCloseAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeManager.getContentPane().add(btnExit);
	}

}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuManager {

	JFrame frmHomeManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuManager window = new FormMenuManager((Integer)null);
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
	public FormMenuManager(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmHomeManager = new JFrame();
		frmHomeManager.setTitle("Home Manager");
		frmHomeManager.setBounds(100, 100, 450, 300);
		frmHomeManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeManager.getContentPane().setLayout(null);
		
		JButton btnAddCustomer = new JButton("Add New Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAddNewCustomer formAddNewCustomer = new FormAddNewCustomer(mgrId);
				formAddNewCustomer.frmAddNewCustomer.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnAddCustomer.setBounds(145, 53, 129, 21);
		frmHomeManager.getContentPane().add(btnAddCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FormRemoveCustomer formRemoveCustomer = new FormRemoveCustomer(mgrId);
					formRemoveCustomer.frmRemoveCustomer.setVisible(true);
					
					frmHomeManager.dispose();
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
			}
		});
		btnRemoveCustomer.setBounds(145, 84, 129, 21);
		frmHomeManager.getContentPane().add(btnRemoveCustomer);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formOpenAccount = new FormMenuOpenAccount(mgrId);
				formOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnOpenAccount.setBounds(145, 115, 129, 21);
		frmHomeManager.getContentPane().add(btnOpenAccount);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCloseAccount formCloseAccount = new FormCloseAccount(mgrId);
				formCloseAccount.frmCloseAccount.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnCloseAccount.setBounds(145, 146, 129, 21);
		frmHomeManager.getContentPane().add(btnCloseAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeManager.getContentPane().add(btnExit);
	}

}

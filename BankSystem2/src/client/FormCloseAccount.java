package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.Customer;
import bus.Manager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormCloseAccount {

	JFrame frmCloseAccount;
	private JTextField textFieldPassword;
	private JTextField textFieldAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCloseAccount window = new FormCloseAccount((Integer)null);
					window.frmCloseAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormCloseAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmCloseAccount = new JFrame();
		frmCloseAccount.setTitle("Close Account");
		frmCloseAccount.setBounds(100, 100, 450, 176);
		frmCloseAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCloseAccount.getContentPane().setLayout(null);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 77, 113, 13);
		frmCloseAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 71, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldPassword);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer accountId = Integer.parseInt(textFieldAccount.getText());
					Manager.closeAccount(accountId);
					JOptionPane.showMessageDialog(null, "Account DELETED!!");
					
					FormMenuManager formMenuManager = new FormMenuManager(mgrId);
					formMenuManager.frmHomeManager.setVisible(true);
					
					frmCloseAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
				
			}
		});
		btnCloseAccount.setBounds(259, 100, 138, 21);
		frmCloseAccount.getContentPane().add(btnCloseAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmCloseAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 100, 77, 21);
		frmCloseAccount.getContentPane().add(btnCancel);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(172, 22, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAccountId = new JLabel("Select the ID of the Account:");
		lblAccountId.setBounds(23, 25, 139, 13);
		frmCloseAccount.getContentPane().add(lblAccountId);
	}
}

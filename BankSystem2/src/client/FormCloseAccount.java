package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormCloseAccount {

	JFrame frmCloseAccount;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCloseAccount window = new FormCloseAccount();
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
	public FormCloseAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCloseAccount = new JFrame();
		frmCloseAccount.setTitle("Close Account");
		frmCloseAccount.setBounds(100, 100, 450, 300);
		frmCloseAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCloseAccount.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Select customer username:");
		lblUsername.setBounds(23, 28, 139, 13);
		frmCloseAccount.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(172, 22, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 195, 113, 13);
		frmCloseAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 189, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldPassword);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.setBounds(259, 218, 138, 21);
		frmCloseAccount.getContentPane().add(btnCloseAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager();
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmCloseAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 218, 77, 21);
		frmCloseAccount.getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(172, 51, 225, 19);
		frmCloseAccount.getContentPane().add(textField);
		
		JLabel lblAccountId = new JLabel("Select the ID of the Account:");
		lblAccountId.setBounds(23, 57, 139, 13);
		frmCloseAccount.getContentPane().add(lblAccountId);
	}
}

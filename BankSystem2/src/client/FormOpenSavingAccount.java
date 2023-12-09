package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormOpenSavingAccount {

	private JFrame frmOpenCheckingAccount;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldInterestRate;
	private JTextField textFieldDueDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenSavingAccount window = new FormOpenSavingAccount();
					window.frmOpenCheckingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenSavingAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOpenCheckingAccount = new JFrame();
		frmOpenCheckingAccount.setTitle("Open Checking Account");
		frmOpenCheckingAccount.setBounds(100, 100, 450, 300);
		frmOpenCheckingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenCheckingAccount.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Select customer username:");
		lblUsername.setBounds(23, 28, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(211, 22, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 195, 113, 13);
		frmOpenCheckingAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 189, 225, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldPassword);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.setBounds(259, 218, 138, 21);
		frmOpenCheckingAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount();
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenCheckingAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 218, 77, 21);
		frmOpenCheckingAccount.getContentPane().add(btnCancel);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(211, 51, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Insert the initial Balance:");
		lblBalance.setBounds(23, 57, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblBalance);
		
		JLabel lblInterestRate = new JLabel("Insert the Interest Rate:");
		lblInterestRate.setBounds(23, 86, 178, 13);
		frmOpenCheckingAccount.getContentPane().add(lblInterestRate);
		
		textFieldInterestRate = new JTextField();
		textFieldInterestRate.setColumns(10);
		textFieldInterestRate.setBounds(211, 80, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldInterestRate);
		
		JLabel lblDueDate = new JLabel("Insert the Due Date:");
		lblDueDate.setBounds(23, 115, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblDueDate);
		
		textFieldDueDate = new JTextField();
		textFieldDueDate.setColumns(10);
		textFieldDueDate.setBounds(211, 109, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldDueDate);
	}
}

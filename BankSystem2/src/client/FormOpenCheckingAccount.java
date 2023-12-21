package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CheckingAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.Manager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class FormOpenCheckingAccount {

	JFrame frmOpenCheckingAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldMonthlyLimit;
	private JTextField textFieldTransFee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenCheckingAccount window = new FormOpenCheckingAccount((Integer)null);
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
	public FormOpenCheckingAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenCheckingAccount = new JFrame();
		frmOpenCheckingAccount.setTitle("Open Checking Account");
		frmOpenCheckingAccount.setBounds(100, 100, 450, 300);
		frmOpenCheckingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenCheckingAccount.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("Select customer ID:");
		lblUserId.setBounds(23, 28, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 22, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 195, 113, 13);
		frmOpenCheckingAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 189, 225, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldPassword);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					Double balance = Double.parseDouble(textFieldBalance.getText());
					Integer monthLimit = Integer.parseInt(textFieldMonthlyLimit.getText());
					Double fee = Double.parseDouble(textFieldTransFee.getText());
					
					Customer selectedCustomer = Customer.search(selectedId);
			
					Manager.openCheckingAccount(selectedCustomer.getId(), balance, monthLimit, fee);
					
					JOptionPane.showMessageDialog(null, "Checking Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenCheckingAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 218, 138, 21);
		frmOpenCheckingAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
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
		
		JLabel lblMonthTransLimit = new JLabel("Insert the Monthly Transactions Limit:");
		lblMonthTransLimit.setBounds(23, 86, 178, 13);
		frmOpenCheckingAccount.getContentPane().add(lblMonthTransLimit);
		
		textFieldMonthlyLimit = new JTextField();
		textFieldMonthlyLimit.setColumns(10);
		textFieldMonthlyLimit.setBounds(211, 80, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldMonthlyLimit);
		
		JLabel lblTransFee = new JLabel("Insert the Transaction Fee:");
		lblTransFee.setBounds(23, 115, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblTransFee);
		
		textFieldTransFee = new JTextField();
		textFieldTransFee.setColumns(10);
		textFieldTransFee.setBounds(211, 109, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldTransFee);
	}
}

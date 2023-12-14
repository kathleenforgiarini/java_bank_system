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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

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
		
		JLabel lblUserId = new JLabel("Customer ID:");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserId.setBounds(38, 61, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 55, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		/*
		 * JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		 * lblPasswordMngr.setBounds(23, 195, 113, 13);
		 * frmOpenCheckingAccount.getContentPane().add(lblPasswordMngr);
		 * 
		 * textFieldPassword = new JTextField(); textFieldPassword.setColumns(10);
		 * textFieldPassword.setBounds(172, 189, 225, 19);
		 * frmOpenCheckingAccount.getContentPane().add(textFieldPassword);
		 */
		
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
		textFieldBalance.setBounds(211, 84, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Initial Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setBounds(38, 90, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblBalance);
		
		JLabel lblMonthTransLimit = new JLabel("Monthly Transactions Limit:");
		lblMonthTransLimit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMonthTransLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMonthTransLimit.setBounds(30, 119, 147, 13);
		frmOpenCheckingAccount.getContentPane().add(lblMonthTransLimit);
		
		textFieldMonthlyLimit = new JTextField();
		textFieldMonthlyLimit.setColumns(10);
		textFieldMonthlyLimit.setBounds(211, 113, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldMonthlyLimit);
		
		JLabel lblTransFee = new JLabel("Transaction Fee:");
		lblTransFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTransFee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTransFee.setBounds(38, 148, 139, 13);
		frmOpenCheckingAccount.getContentPane().add(lblTransFee);
		
		textFieldTransFee = new JTextField();
		textFieldTransFee.setColumns(10);
		textFieldTransFee.setBounds(211, 142, 186, 19);
		frmOpenCheckingAccount.getContentPane().add(textFieldTransFee);
		
		JLabel lblInformation = new JLabel("New Checking Account");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(23, 11, 374, 19);
		frmOpenCheckingAccount.getContentPane().add(lblInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmOpenCheckingAccount.getContentPane().add(lblNewLabel);
		
		
	}
}

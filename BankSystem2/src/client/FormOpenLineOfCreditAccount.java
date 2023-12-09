package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormOpenLineOfCreditAccount {

	private JFrame frmOpenLineOfCreditAccount;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldDueDate;
	private JTextField textFieldLimit;
	private JTextField textFieldInterestRate;
	private JTextField textFieldNbInstallments;
	private JTextField textFieldAmountInstallment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenLineOfCreditAccount window = new FormOpenLineOfCreditAccount();
					window.frmOpenLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenLineOfCreditAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOpenLineOfCreditAccount = new JFrame();
		frmOpenLineOfCreditAccount.setTitle("Open Line Of Credit Account");
		frmOpenLineOfCreditAccount.setBounds(100, 100, 450, 337);
		frmOpenLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Select customer username:");
		lblUsername.setBounds(23, 28, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(211, 22, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 248, 113, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 242, 225, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldPassword);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.setBounds(259, 271, 138, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount();
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenLineOfCreditAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 271, 77, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnCancel);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(211, 51, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Insert the initial Balance:");
		lblBalance.setBounds(23, 57, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblBalance);
		
		JLabel lblDueDate = new JLabel("Insert the Due Date:");
		lblDueDate.setBounds(23, 86, 178, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDate);
		
		textFieldDueDate = new JTextField();
		textFieldDueDate.setColumns(10);
		textFieldDueDate.setBounds(211, 80, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldDueDate);
		
		JLabel lblLimit = new JLabel("Insert the Limit:");
		lblLimit.setBounds(23, 115, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblLimit);
		
		textFieldLimit = new JTextField();
		textFieldLimit.setColumns(10);
		textFieldLimit.setBounds(211, 109, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldLimit);
		
		textFieldInterestRate = new JTextField();
		textFieldInterestRate.setColumns(10);
		textFieldInterestRate.setBounds(211, 138, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldInterestRate);
		
		JLabel lblInterestRate = new JLabel("Insert the Interest Rate:");
		lblInterestRate.setBounds(23, 144, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblInterestRate);
		
		JLabel lblNbInstallments = new JLabel("Insert the Number Of Installments:");
		lblNbInstallments.setBounds(23, 173, 178, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblNbInstallments);
		
		textFieldNbInstallments = new JTextField();
		textFieldNbInstallments.setColumns(10);
		textFieldNbInstallments.setBounds(211, 167, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldNbInstallments);
		
		textFieldAmountInstallment = new JTextField();
		textFieldAmountInstallment.setColumns(10);
		textFieldAmountInstallment.setBounds(211, 196, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldAmountInstallment);
		
		JLabel lblValueInstallment = new JLabel("Amount for each Installment:");
		lblValueInstallment.setBounds(23, 202, 178, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblValueInstallment);
	}
}

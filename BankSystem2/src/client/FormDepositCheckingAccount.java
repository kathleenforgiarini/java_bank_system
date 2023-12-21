package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CheckingAccount;

public class FormDepositCheckingAccount {

	JFrame frmDepositCheckingAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositCheckingAccount window = new FormDepositCheckingAccount((Integer)null);
					window.frmDepositCheckingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositCheckingAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositCheckingAccount = new JFrame();
		frmDepositCheckingAccount.setTitle("Deposit On Checking Account");
		frmDepositCheckingAccount.setBounds(100, 100, 450, 300);
		frmDepositCheckingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositCheckingAccount.getContentPane().setLayout(null);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CheckingAccount account = CheckingAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.deposit(amount);
						
						JOptionPane.showMessageDialog(null, "Deposit successfully! \nNew Balance: " + 
						Account.getBalance(selectedAccount, customerId));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnDeposit.setBounds(304, 205, 85, 21);
		frmDepositCheckingAccount.getContentPane().add(btnDeposit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositCheckingAccount.dispose();
			}
		});
		btnExit.setBounds(171, 205, 85, 21);
		frmDepositCheckingAccount.getContentPane().add(btnExit);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(21, 27, 104, 13);
		frmDepositCheckingAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setBounds(133, 24, 186, 19);
		frmDepositCheckingAccount.getContentPane().add(textFieldAccount);
		textFieldAccount.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(21, 53, 104, 13);
		frmDepositCheckingAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(133, 50, 186, 19);
		frmDepositCheckingAccount.getContentPane().add(textFieldAmount);
	}

}

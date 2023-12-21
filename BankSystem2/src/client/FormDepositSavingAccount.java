package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import bus.Account;
import bus.SavingAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDepositSavingAccount {

	JFrame frmDepositSavingAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositSavingAccount window = new FormDepositSavingAccount((Integer)null);
					window.frmDepositSavingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositSavingAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositSavingAccount = new JFrame();
		frmDepositSavingAccount.setTitle("Deposit Into a Saving Account");
		frmDepositSavingAccount.setBounds(100, 100, 450, 300);
		frmDepositSavingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositSavingAccount.getContentPane().setLayout(null);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(141, 26, 186, 19);
		frmDepositSavingAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(29, 29, 104, 13);
		frmDepositSavingAccount.getContentPane().add(lblAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(29, 55, 104, 13);
		frmDepositSavingAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(141, 52, 186, 19);
		frmDepositSavingAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositSavingAccount.dispose();
			}
		});
		btnExit.setBounds(179, 207, 85, 21);
		frmDepositSavingAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					SavingAccount account = SavingAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnDeposit.setBounds(312, 207, 85, 21);
		frmDepositSavingAccount.getContentPane().add(btnDeposit);
	}
}

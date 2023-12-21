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

public class FormWithdrawSavingAccount {

	JFrame frmWithdrawSavingAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawSavingAccount window = new FormWithdrawSavingAccount((Integer)null);
					window.frmWithdrawSavingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawSavingAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawSavingAccount = new JFrame();
		frmWithdrawSavingAccount.setTitle("Withdraw From a Saving Account");
		frmWithdrawSavingAccount.setBounds(100, 100, 450, 300);
		frmWithdrawSavingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawSavingAccount.getContentPane().setLayout(null);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(143, 29, 186, 19);
		frmWithdrawSavingAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(31, 32, 104, 13);
		frmWithdrawSavingAccount.getContentPane().add(lblAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(31, 58, 104, 13);
		frmWithdrawSavingAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(143, 55, 186, 19);
		frmWithdrawSavingAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmWithdrawSavingAccount.dispose();
			}
		});
		btnExit.setBounds(181, 210, 85, 21);
		frmWithdrawSavingAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					SavingAccount account = SavingAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.withdraw(amount);
						
						JOptionPane.showMessageDialog(null, "Withdraw successfully! \nNew Balance: " + 
						Account.getBalance(selectedAccount, customerId));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnWithdraw.setBounds(314, 210, 85, 21);
		frmWithdrawSavingAccount.getContentPane().add(btnWithdraw);
	}

}

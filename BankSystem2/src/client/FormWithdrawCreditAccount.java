package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CreditAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormWithdrawCreditAccount {

	JFrame frmWithdrawCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawCreditAccount window = new FormWithdrawCreditAccount((Integer)null);
					window.frmWithdrawCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawCreditAccount = new JFrame();
		frmWithdrawCreditAccount.setTitle("Withdraw From a Credit Account");
		frmWithdrawCreditAccount.setBounds(100, 100, 450, 300);
		frmWithdrawCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(31, 30, 104, 13);
		frmWithdrawCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setBounds(143, 27, 186, 19);
		textFieldAccount.setColumns(10);
		frmWithdrawCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(31, 56, 104, 13);
		frmWithdrawCreditAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(143, 53, 186, 19);
		textFieldAmount.setColumns(10);
		frmWithdrawCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmWithdrawCreditAccount.dispose();
			}
		});
		btnExit.setBounds(181, 208, 85, 21);
		frmWithdrawCreditAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CreditAccount account = CreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.withdraw(amount);
						
						JOptionPane.showMessageDialog(null, "Withdraw successfully! \n" +
								"The value of your invoice, at this moment, is : " + 
								Account.getBalance(selectedAccount, customerId)*(-1));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnWithdraw.setBounds(314, 208, 85, 21);
		frmWithdrawCreditAccount.getContentPane().add(btnWithdraw);
	}

}

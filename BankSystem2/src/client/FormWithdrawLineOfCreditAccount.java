package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.LineOfCreditAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormWithdrawLineOfCreditAccount {

	JFrame frmWithdrawLineOfCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawLineOfCreditAccount window = new FormWithdrawLineOfCreditAccount((Integer)null);
					window.frmWithdrawLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawLineOfCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawLineOfCreditAccount = new JFrame();
		frmWithdrawLineOfCreditAccount.setTitle("Withdraw From a Line Of Credit Account");
		frmWithdrawLineOfCreditAccount.setBounds(100, 100, 450, 300);
		frmWithdrawLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(33, 30, 104, 13);
		frmWithdrawLineOfCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(145, 27, 186, 19);
		frmWithdrawLineOfCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(33, 56, 104, 13);
		frmWithdrawLineOfCreditAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(145, 53, 186, 19);
		frmWithdrawLineOfCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmWithdrawLineOfCreditAccount.dispose();
			}
		});
		btnExit.setBounds(183, 208, 85, 21);
		frmWithdrawLineOfCreditAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					LineOfCreditAccount account = LineOfCreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnWithdraw.setBounds(316, 208, 85, 21);
		frmWithdrawLineOfCreditAccount.getContentPane().add(btnWithdraw);
	}

}

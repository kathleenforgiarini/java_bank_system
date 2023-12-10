package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CurrencyAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormWithdrawCurrencyAccount {

	JFrame frmWithdrawCurrencyAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawCurrencyAccount window = new FormWithdrawCurrencyAccount((Integer)null);
					window.frmWithdrawCurrencyAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawCurrencyAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawCurrencyAccount = new JFrame();
		frmWithdrawCurrencyAccount.setTitle("Withdraw From a Currency Account");
		frmWithdrawCurrencyAccount.setBounds(100, 100, 450, 300);
		frmWithdrawCurrencyAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawCurrencyAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(30, 32, 104, 13);
		frmWithdrawCurrencyAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(142, 29, 186, 19);
		frmWithdrawCurrencyAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(30, 58, 104, 13);
		frmWithdrawCurrencyAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(142, 55, 186, 19);
		frmWithdrawCurrencyAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmWithdrawCurrencyAccount.dispose();
			}
		});
		btnExit.setBounds(180, 210, 85, 21);
		frmWithdrawCurrencyAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CurrencyAccount account = CurrencyAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnWithdraw.setBounds(313, 210, 85, 21);
		frmWithdrawCurrencyAccount.getContentPane().add(btnWithdraw);
	}

}

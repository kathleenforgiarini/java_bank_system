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

public class FormDepositCurrencyAccount {

	JFrame frmDepositCurrencyAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositCurrencyAccount window = new FormDepositCurrencyAccount((Integer)null);
					window.frmDepositCurrencyAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositCurrencyAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositCurrencyAccount = new JFrame();
		frmDepositCurrencyAccount.setTitle("Deposit On Currency Account");
		frmDepositCurrencyAccount.setBounds(100, 100, 450, 300);
		frmDepositCurrencyAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositCurrencyAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(33, 33, 104, 13);
		frmDepositCurrencyAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(145, 30, 186, 19);
		frmDepositCurrencyAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(33, 59, 104, 13);
		frmDepositCurrencyAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(145, 56, 186, 19);
		frmDepositCurrencyAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositCurrencyAccount.dispose();
			}
		});
		btnExit.setBounds(183, 211, 85, 21);
		frmDepositCurrencyAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CurrencyAccount account = CurrencyAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnDeposit.setBounds(316, 211, 85, 21);
		frmDepositCurrencyAccount.getContentPane().add(btnDeposit);
	}
}

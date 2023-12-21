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

public class FormDepositCreditAccount {

	JFrame frmDepositCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositCreditAccount window = new FormDepositCreditAccount((Integer)null);
					window.frmDepositCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositCreditAccount = new JFrame();
		frmDepositCreditAccount.setTitle("Deposit On Credit Account");
		frmDepositCreditAccount.setBounds(100, 100, 450, 300);
		frmDepositCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(32, 31, 104, 13);
		frmDepositCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(144, 28, 186, 19);
		frmDepositCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(32, 57, 104, 13);
		frmDepositCreditAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(144, 54, 186, 19);
		frmDepositCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositCreditAccount.dispose();
			}
		});
		btnExit.setBounds(182, 209, 85, 21);
		frmDepositCreditAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CreditAccount account = CreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.deposit(amount);
						JOptionPane.showMessageDialog(null, "Payment received!\n" +
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
		btnDeposit.setBounds(315, 209, 85, 21);
		frmDepositCreditAccount.getContentPane().add(btnDeposit);
	}

}

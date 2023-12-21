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

public class FormDepositLineOfCreditAccount {

	JFrame frmDepositLineOfCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositLineOfCreditAccount window = new FormDepositLineOfCreditAccount((Integer)null);
					window.frmDepositLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositLineOfCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositLineOfCreditAccount = new JFrame();
		frmDepositLineOfCreditAccount.setTitle("Deposit On Line Of Credit Account");
		frmDepositLineOfCreditAccount.setBounds(100, 100, 450, 300);
		frmDepositLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setBounds(34, 35, 104, 13);
		frmDepositLineOfCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(146, 32, 186, 19);
		frmDepositLineOfCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblNewLabel = new JLabel("Enter the amount:");
		lblNewLabel.setBounds(34, 61, 104, 13);
		frmDepositLineOfCreditAccount.getContentPane().add(lblNewLabel);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(146, 58, 186, 19);
		frmDepositLineOfCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositLineOfCreditAccount.dispose();
			}
		});
		btnExit.setBounds(184, 213, 85, 21);
		frmDepositLineOfCreditAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					LineOfCreditAccount account = LineOfCreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnDeposit.setBounds(317, 213, 85, 21);
		frmDepositLineOfCreditAccount.getContentPane().add(btnDeposit);
	}

}

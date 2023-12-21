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

public class FormTransferBetweenChecking {

	JFrame frmTransferBetweenChecking;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTransferBetweenChecking window = new FormTransferBetweenChecking((Integer)null);
					window.frmTransferBetweenChecking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormTransferBetweenChecking(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmTransferBetweenChecking = new JFrame();
		frmTransferBetweenChecking.setTitle("Transfer between Checking Accounts");
		frmTransferBetweenChecking.setBounds(100, 100, 450, 300);
		frmTransferBetweenChecking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransferBetweenChecking.getContentPane().setLayout(null);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccountFrom = Integer.parseInt(textFieldFrom.getText());
					Integer selectedAccountTo = Integer.parseInt(textFieldTo.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					if (selectedAccountFrom != selectedAccountTo) {
						CheckingAccount accountFrom = CheckingAccount.searchByIdAndCustomer(selectedAccountFrom, customerId);
						CheckingAccount accountTo = CheckingAccount.search(selectedAccountTo);
						
						if (accountFrom != null && accountTo != null) {
							accountFrom.withdraw(amount);
							JOptionPane.showMessageDialog(null, "Withdraw successfully from account " +
							selectedAccountFrom + "\nNew Balance: " + 
									Account.getBalance(selectedAccountFrom, customerId));
							
							accountTo.deposit(amount);
							JOptionPane.showMessageDialog(null, "Deposit successfully to account " +
									selectedAccountTo);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Invalid account number!");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "You must enter two different accounts!");
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
			}
		});
		btnTransfer.setBounds(315, 219, 85, 21);
		frmTransferBetweenChecking.getContentPane().add(btnTransfer);
		
		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setBounds(39, 37, 45, 13);
		frmTransferBetweenChecking.getContentPane().add(lblNewLabel);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setBounds(112, 10, 66, 13);
		frmTransferBetweenChecking.getContentPane().add(lblAccountId);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(39, 70, 45, 13);
		frmTransferBetweenChecking.getContentPane().add(lblTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(83, 34, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(83, 67, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldTo);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(83, 110, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldAmount);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(27, 113, 45, 13);
		frmTransferBetweenChecking.getContentPane().add(lblTo_1);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTransfer formMenuTransfer = new FormMenuTransfer(customerId);
				formMenuTransfer.frmMenuTransfer.setVisible(true);
				
				frmTransferBetweenChecking.dispose();
			}
		});
		btnExit.setBounds(209, 219, 85, 21);
		frmTransferBetweenChecking.getContentPane().add(btnExit);
	}
}

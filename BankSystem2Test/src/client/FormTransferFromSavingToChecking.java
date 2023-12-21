package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CheckingAccount;
import bus.SavingAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormTransferFromSavingToChecking {

	JFrame frmTransferFromSavingToChecking;
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
					FormTransferFromSavingToChecking window = new FormTransferFromSavingToChecking((Integer)null);
					window.frmTransferFromSavingToChecking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormTransferFromSavingToChecking(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmTransferFromSavingToChecking = new JFrame();
		frmTransferFromSavingToChecking.setTitle("Transfer From Saving to Checking Account");
		frmTransferFromSavingToChecking.setBounds(100, 100, 450, 300);
		frmTransferFromSavingToChecking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransferFromSavingToChecking.getContentPane().setLayout(null);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setBounds(117, 23, 66, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblAccountId);
		
		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setBounds(44, 50, 45, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblNewLabel);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(88, 47, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldFrom);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(88, 80, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldTo);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(88, 123, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldAmount);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(32, 126, 45, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblTo_1);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(44, 83, 45, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblTo);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTransfer formMenuTransfer = new FormMenuTransfer(customerId);
				formMenuTransfer.frmMenuTransfer.setVisible(true);
				
				frmTransferFromSavingToChecking.dispose();
			}
		});
		btnExit.setBounds(214, 232, 85, 21);
		frmTransferFromSavingToChecking.getContentPane().add(btnExit);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccountFrom = Integer.parseInt(textFieldFrom.getText());
					Integer selectedAccountTo = Integer.parseInt(textFieldTo.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					if (selectedAccountFrom != selectedAccountTo) {
					
						SavingAccount accountFrom = SavingAccount.searchByIdAndCustomer(selectedAccountFrom, customerId);
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
		btnTransfer.setBounds(320, 232, 85, 21);
		frmTransferFromSavingToChecking.getContentPane().add(btnTransfer);
	}

}

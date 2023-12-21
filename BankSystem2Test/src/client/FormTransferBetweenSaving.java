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

public class FormTransferBetweenSaving {

	JFrame frmTransferBetweenSaving;
	private JTextField textFieldAmount;
	private JTextField textFieldTo;
	private JTextField textFieldFrom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTransferBetweenSaving window = new FormTransferBetweenSaving((Integer)null);
					window.frmTransferBetweenSaving.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormTransferBetweenSaving(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmTransferBetweenSaving = new JFrame();
		frmTransferBetweenSaving.setTitle("Transfer Between Saving Accounts");
		frmTransferBetweenSaving.setBounds(100, 100, 450, 300);
		frmTransferBetweenSaving.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransferBetweenSaving.getContentPane().setLayout(null);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setBounds(120, 10, 66, 13);
		frmTransferBetweenSaving.getContentPane().add(lblAccountId);
		
		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setBounds(47, 37, 45, 13);
		frmTransferBetweenSaving.getContentPane().add(lblNewLabel);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(47, 70, 45, 13);
		frmTransferBetweenSaving.getContentPane().add(lblTo);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(35, 113, 45, 13);
		frmTransferBetweenSaving.getContentPane().add(lblTo_1);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(91, 110, 127, 19);
		frmTransferBetweenSaving.getContentPane().add(textFieldAmount);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(91, 67, 127, 19);
		frmTransferBetweenSaving.getContentPane().add(textFieldTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(91, 34, 127, 19);
		frmTransferBetweenSaving.getContentPane().add(textFieldFrom);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTransfer formMenuTransfer = new FormMenuTransfer(customerId);
				formMenuTransfer.frmMenuTransfer.setVisible(true);
				
				frmTransferBetweenSaving.dispose();
			}
		});
		btnExit.setBounds(217, 219, 85, 21);
		frmTransferBetweenSaving.getContentPane().add(btnExit);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccountFrom = Integer.parseInt(textFieldFrom.getText());
					Integer selectedAccountTo = Integer.parseInt(textFieldTo.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					if (selectedAccountFrom != selectedAccountTo) {
					
						SavingAccount accountFrom = SavingAccount.searchByIdAndCustomer(selectedAccountFrom, customerId);
						SavingAccount accountTo = SavingAccount.search(selectedAccountTo);
						
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
		btnTransfer.setBounds(323, 219, 85, 21);
		frmTransferBetweenSaving.getContentPane().add(btnTransfer);
	}

}

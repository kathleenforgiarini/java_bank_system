package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bus.Account;
import bus.CheckingAccount;
import bus.SavingAccount;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class FormTransferFromCheckingToSaving {

	JFrame frmTransferFromCheckingToSaving;
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
					FormTransferFromCheckingToSaving window = new FormTransferFromCheckingToSaving((Integer)null);
					window.frmTransferFromCheckingToSaving.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormTransferFromCheckingToSaving(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmTransferFromCheckingToSaving = new JFrame();
		frmTransferFromCheckingToSaving.setTitle("Transfer From Checking to Saving Account");
		frmTransferFromCheckingToSaving.setBounds(100, 100, 450, 300);
		frmTransferFromCheckingToSaving.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransferFromCheckingToSaving.getContentPane().setLayout(null);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccountId.setBounds(169, 25, 66, 13);
		frmTransferFromCheckingToSaving.getContentPane().add(lblAccountId);
		
		JLabel lblFrom = new JLabel("FROM");
		lblFrom.setBounds(96, 52, 45, 13);
		frmTransferFromCheckingToSaving.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(96, 85, 45, 13);
		frmTransferFromCheckingToSaving.getContentPane().add(lblTo);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(84, 128, 45, 13);
		frmTransferFromCheckingToSaving.getContentPane().add(lblTo_1);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(140, 125, 127, 19);
		frmTransferFromCheckingToSaving.getContentPane().add(textFieldAmount);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(140, 82, 127, 19);
		frmTransferFromCheckingToSaving.getContentPane().add(textFieldTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(140, 49, 127, 19);
		frmTransferFromCheckingToSaving.getContentPane().add(textFieldFrom);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTransfer formMenuTransfer = new FormMenuTransfer(customerId);
				formMenuTransfer.frmMenuTransfer.setVisible(true);
				
				frmTransferFromCheckingToSaving.dispose();
			}
		});
		btnExit.setBounds(212, 232, 85, 21);
		frmTransferFromCheckingToSaving.getContentPane().add(btnExit);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccountFrom = Integer.parseInt(textFieldFrom.getText());
					Integer selectedAccountTo = Integer.parseInt(textFieldTo.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					if (selectedAccountFrom != selectedAccountTo) {
					
						CheckingAccount accountFrom = CheckingAccount.searchByIdAndCustomer(selectedAccountFrom, customerId);
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
		btnTransfer.setBounds(318, 232, 85, 21);
		frmTransferFromCheckingToSaving.getContentPane().add(btnTransfer);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmTransferFromCheckingToSaving.getContentPane().add(lblNewLabel);
	}

}

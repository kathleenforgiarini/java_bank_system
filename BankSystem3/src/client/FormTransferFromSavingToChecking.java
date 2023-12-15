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
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccountId.setBounds(167, 33, 66, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblAccountId);
		
		JLabel lblFrom = new JLabel("FROM");
		lblFrom.setBounds(94, 60, 45, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblFrom);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(138, 57, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldFrom);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(138, 90, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldTo);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(138, 133, 127, 19);
		frmTransferFromSavingToChecking.getContentPane().add(textFieldAmount);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(82, 136, 45, 13);
		frmTransferFromSavingToChecking.getContentPane().add(lblTo_1);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(94, 93, 45, 13);
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
					JOptionPane.showMessageDialog(null, "The fields can not be empty\nIt must be a number");
				}
			}
		});
		btnTransfer.setBounds(320, 232, 85, 21);
		frmTransferFromSavingToChecking.getContentPane().add(btnTransfer);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmTransferFromSavingToChecking.getContentPane().add(lblNewLabel);
		
	}

}

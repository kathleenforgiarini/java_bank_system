package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bus.Account;
import bus.CheckingAccount;
import java.awt.Font;
import java.awt.Image;

public class FormTransferBetweenChecking {

	JFrame frmTransferBetweenChecking;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTextField textFieldAmount;
	private JLabel lblFrom;

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
		
		
		lblFrom = new JLabel("FROM");
		lblFrom.setBounds(109, 51, 45, 13);
		frmTransferBetweenChecking.getContentPane().add(lblFrom);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccountId.setBounds(182, 24, 66, 13);
		frmTransferBetweenChecking.getContentPane().add(lblAccountId);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setBounds(109, 84, 45, 13);
		frmTransferBetweenChecking.getContentPane().add(lblTo);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(153, 48, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(153, 81, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldTo);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(153, 124, 127, 19);
		frmTransferBetweenChecking.getContentPane().add(textFieldAmount);
		
		JLabel lblTo_1 = new JLabel("Amount");
		lblTo_1.setBounds(97, 127, 45, 13);
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmTransferBetweenChecking.getContentPane().add(lblNewLabel);
		
	}
}

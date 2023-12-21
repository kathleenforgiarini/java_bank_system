package client;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CreditAccount;
import bus.ExceptionNotEnoughBalance;
import bus.ExceptionNotEnoughLimit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormWithdrawCreditAccount {

	JFrame frmWithdrawCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;
	private JLabel lblAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawCreditAccount window = new FormWithdrawCreditAccount((Integer)null);
					window.frmWithdrawCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawCreditAccount = new JFrame();
		frmWithdrawCreditAccount.setTitle("Withdraw From a Credit Account");
		frmWithdrawCreditAccount.setBounds(100, 100, 450, 300);
		frmWithdrawCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Account id:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(28, 38, 124, 13);
		frmWithdrawCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setBounds(160, 35, 186, 19);
		textFieldAccount.setColumns(10);
		frmWithdrawCreditAccount.getContentPane().add(textFieldAccount);
		
		
		lblAmount = new JLabel("Amount to Withdraw:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(28, 75, 124, 13);
		frmWithdrawCreditAccount.getContentPane().add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(160, 72, 186, 19);
		textFieldAmount.setColumns(10);
		frmWithdrawCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuWithdraw formMenuWithdraw = new FormMenuWithdraw(customerId);
				formMenuWithdraw.frmMenuWithdraw.setVisible(true);
				frmWithdrawCreditAccount.dispose();
			}
		});
		btnExit.setBounds(181, 208, 85, 21);
		frmWithdrawCreditAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CreditAccount account = CreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.withdraw(amount);
						
						JOptionPane.showMessageDialog(null, "Withdraw successfully! \n" +
								"The value of your invoice, at this moment, is : " + 
								Account.getBalance(selectedAccount, customerId)*(-1));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
					
				} 
				catch(ExceptionNotEnoughLimit ex) {
					JOptionPane.showMessageDialog(null, "You don't have enough limit!");
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "The field must not be empty.\nIt must be a number.");
				}
			}
		});
		btnWithdraw.setBounds(287, 208, 112, 21);
		frmWithdrawCreditAccount.getContentPane().add(btnWithdraw);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmWithdrawCreditAccount.getContentPane().add(lblNewLabel);
	}

}

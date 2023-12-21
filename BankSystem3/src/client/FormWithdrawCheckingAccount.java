package client;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CheckingAccount;
import bus.ExceptionNotEnoughBalance;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormWithdrawCheckingAccount {

	JFrame frmWithdrawCheckingAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormWithdrawCheckingAccount window = new FormWithdrawCheckingAccount((Integer)null);
					window.frmWithdrawCheckingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormWithdrawCheckingAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmWithdrawCheckingAccount = new JFrame();
		frmWithdrawCheckingAccount.setTitle("Withdraw From a Checking Account");
		frmWithdrawCheckingAccount.setBounds(100, 100, 450, 300);
		frmWithdrawCheckingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWithdrawCheckingAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Account id:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(26, 34, 122, 13);
		frmWithdrawCheckingAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(172, 30, 186, 19);
		frmWithdrawCheckingAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAmount = new JLabel("Amount to withdraw:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(26, 72, 122, 13);
		frmWithdrawCheckingAccount.getContentPane().add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(172, 68, 186, 19);
		frmWithdrawCheckingAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuWithdraw formMenuWithdraw = new FormMenuWithdraw(customerId);
				formMenuWithdraw.frmMenuWithdraw.setVisible(true);
				frmWithdrawCheckingAccount.dispose();
			}
		});
		btnExit.setBounds(179, 208, 85, 21);
		frmWithdrawCheckingAccount.getContentPane().add(btnExit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CheckingAccount account = CheckingAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.withdraw(amount);
						
						JOptionPane.showMessageDialog(null, "Withdraw successfully! \nNew Balance: " + 
						Account.getBalance(selectedAccount, customerId));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
				}
				catch(ExceptionNotEnoughBalance ex) {
					JOptionPane.showMessageDialog(null, "Not enough funds to complete the operation!");
				}	
				catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "The field must not be empty.\nIt must be a number.");
				}
			}
		});
		btnWithdraw.setBounds(297, 208, 100, 21);
		frmWithdrawCheckingAccount.getContentPane().add(btnWithdraw);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmWithdrawCheckingAccount.getContentPane().add(lblNewLabel);
		
	}

}

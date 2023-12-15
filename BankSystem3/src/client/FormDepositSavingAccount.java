package client;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;

import bus.Account;
import bus.SavingAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormDepositSavingAccount {

	JFrame frmDepositSavingAccount;
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
					FormDepositSavingAccount window = new FormDepositSavingAccount((Integer)null);
					window.frmDepositSavingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositSavingAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositSavingAccount = new JFrame();
		frmDepositSavingAccount.setTitle("Deposit Into a Saving Account");
		frmDepositSavingAccount.setBounds(100, 100, 450, 300);
		frmDepositSavingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositSavingAccount.getContentPane().setLayout(null);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(175, 29, 186, 19);
		frmDepositSavingAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAccount = new JLabel("Enter the account id:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(10, 33, 133, 13);
		frmDepositSavingAccount.getContentPane().add(lblAccount);
		
		
		lblAmount = new JLabel("Amount to deposit:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(10, 72, 133, 13);
		frmDepositSavingAccount.getContentPane().add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(175, 68, 186, 19);
		frmDepositSavingAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositSavingAccount.dispose();
			}
		});
		btnExit.setBounds(179, 207, 85, 21);
		frmDepositSavingAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					SavingAccount account = SavingAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.deposit(amount);
						
						JOptionPane.showMessageDialog(null, "Deposit successfully! \nNew Balance: " + 
						Account.getBalance(selectedAccount, customerId));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "The fields can not be empty\nIt must be a number");
				}
			}
		});
		btnDeposit.setBounds(312, 207, 85, 21);
		frmDepositSavingAccount.getContentPane().add(btnDeposit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 130, 107, 105);
		frmDepositSavingAccount.getContentPane().add(lblNewLabel);
	}
}

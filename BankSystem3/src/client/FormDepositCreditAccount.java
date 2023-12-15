package client;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CreditAccount;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormDepositCreditAccount {

	JFrame frmDepositCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositCreditAccount window = new FormDepositCreditAccount((Integer)null);
					window.frmDepositCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositCreditAccount = new JFrame();
		frmDepositCreditAccount.setTitle("Deposit On Credit Account");
		frmDepositCreditAccount.setBounds(100, 100, 450, 300);
		frmDepositCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Account id:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(50, 35, 104, 13);
		frmDepositCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(177, 31, 186, 19);
		frmDepositCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAmount = new JLabel("Enter the amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(50, 69, 104, 13);
		frmDepositCreditAccount.getContentPane().add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(177, 65, 186, 19);
		frmDepositCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositCreditAccount.dispose();
			}
		});
		btnExit.setBounds(182, 209, 85, 21);
		frmDepositCreditAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					CreditAccount account = CreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
					if (account != null) {
						account.deposit(amount);
						JOptionPane.showMessageDialog(null, "Payment received!\n" +
								"The value of your invoice, at this moment, is : " +
								Account.getBalance(selectedAccount, customerId)*(-1));
					} else {
						JOptionPane.showMessageDialog(null, "You don't have this account number, try again!");
					}
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "The fields can not be empty\nIt must be a number");
				}
			}
		});
		btnDeposit.setBounds(315, 209, 85, 21);
		frmDepositCreditAccount.getContentPane().add(btnDeposit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 130, 107, 105);
		frmDepositCreditAccount.getContentPane().add(lblNewLabel);
		
	}

}

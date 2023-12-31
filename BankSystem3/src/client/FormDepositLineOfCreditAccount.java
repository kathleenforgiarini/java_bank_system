package client;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.LineOfCreditAccount;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormDepositLineOfCreditAccount {

	JFrame frmDepositLineOfCreditAccount;
	private JTextField textFieldAccount;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDepositLineOfCreditAccount window = new FormDepositLineOfCreditAccount((Integer)null);
					window.frmDepositLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDepositLineOfCreditAccount(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmDepositLineOfCreditAccount = new JFrame();
		frmDepositLineOfCreditAccount.setTitle("Deposit On Line Of Credit Account");
		frmDepositLineOfCreditAccount.setBounds(100, 100, 450, 300);
		frmDepositLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepositLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Account id:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(39, 41, 122, 13);
		frmDepositLineOfCreditAccount.getContentPane().add(lblAccount);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(169, 38, 186, 19);
		frmDepositLineOfCreditAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAmount = new JLabel("Amount to deposit:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(39, 79, 122, 13);
		frmDepositLineOfCreditAccount.getContentPane().add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(169, 76, 186, 19);
		frmDepositLineOfCreditAccount.getContentPane().add(textFieldAmount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit formMenuDeposit = new FormMenuDeposit(customerId);
				formMenuDeposit.frmMenuDeposit.setVisible(true);
				frmDepositLineOfCreditAccount.dispose();
			}
		});
		btnExit.setBounds(184, 213, 85, 21);
		frmDepositLineOfCreditAccount.getContentPane().add(btnExit);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedAccount = Integer.parseInt(textFieldAccount.getText());
					Double amount = Double.parseDouble(textFieldAmount.getText());
					
					LineOfCreditAccount account = LineOfCreditAccount.searchByIdAndCustomer(selectedAccount, customerId);
					
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
		btnDeposit.setBounds(317, 213, 85, 21);
		frmDepositLineOfCreditAccount.getContentPane().add(btnDeposit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 130, 107, 105);
		frmDepositLineOfCreditAccount.getContentPane().add(lblNewLabel);
	}

}

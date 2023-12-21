package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;
import bus.LineOfCreditAccount;
import bus.SavingAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class FormGetBalance {

	JFrame frmGetBalance;
	private JTextField textFieldAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormGetBalance window = new FormGetBalance((Integer)null);
					window.frmGetBalance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormGetBalance(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmGetBalance = new JFrame();
		frmGetBalance.setTitle("Get Balance");
		frmGetBalance.setBounds(100, 100, 365, 154);
		frmGetBalance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGetBalance.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter the account Id:");
		lblNewLabel.setBounds(23, 21, 136, 13);
		frmGetBalance.getContentPane().add(lblNewLabel);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setBounds(169, 18, 127, 19);
		frmGetBalance.getContentPane().add(textFieldAccount);
		textFieldAccount.setColumns(10);
		
		JButton btnViewBalance = new JButton("View balance");
		btnViewBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldAccount.getText());
					Double balance = Account.getBalance(selectedId, customerId);
					String type = Account.getType(selectedId, customerId);
					
					if (balance != null)
					{
						if (type.equals("SavingAccount"))
						{
							SavingAccount savingAccount = SavingAccount.searchByIdAndCustomer(selectedId, customerId);
							Double gain = savingAccount.getGain();
							JOptionPane.showMessageDialog(null, "Your balance is: " + balance
									+ "\nYour gain is: " + gain
									+ "\nThis amount will be available on " + savingAccount.getDueDate());
						}
						else if (type.equals("CreditAccount"))
						{
							CreditAccount creditAccount = CreditAccount.searchByIdAndCustomer(selectedId, customerId);
							Double limit = creditAccount.getLimit();
							LocalDate dueDate = creditAccount.getDueDate();
							JOptionPane.showMessageDialog(null, "The value of your invoice, at this moment, is : " + balance*(-1)
									+ "\nYou have " + (balance - limit) + " to spend until your Due Date."
									+ "\nYour next due date will be on " + dueDate);
						}
						else if (type.equals("CurrencyAccount")) {
							CurrencyAccount currencyAccount = CurrencyAccount.searchByIdAndCustomer(selectedId, customerId);
							String currency = currencyAccount.getCurrency().toString();
							JOptionPane.showMessageDialog(null, "Your balance is : " + currency + " "
									+ balance);
						}
						else if (type.equals("LineOfCreditAccount")) {
							LineOfCreditAccount lineofcreditAccount = LineOfCreditAccount.searchByIdAndCustomer(selectedId, customerId);
							Double installment = lineofcreditAccount.getInstallment();
							Integer nbInstallments = lineofcreditAccount.getNbOfInstallments();
							JOptionPane.showMessageDialog(null, "Your balance is: " + balance
									+ "\nThe value of your installment is: " + installment
									+ "\nThe number of installments remaining is: " + nbInstallments);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Your balance is: " + balance);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "You don't have this account id, try again!");
					}
					
					
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
			}
		});
		btnViewBalance.setBounds(211, 72, 112, 21);
		frmGetBalance.getContentPane().add(btnViewBalance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				frmGetBalance.dispose();
			}
		});
		btnExit.setBounds(98, 72, 85, 21);
		frmGetBalance.getContentPane().add(btnExit);
	}
}

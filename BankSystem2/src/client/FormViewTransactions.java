package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import bus.Account;
import bus.Customer;
import bus.PredicateAmount;
import bus.PredicateDate;
import bus.Transaction;
import bus.TransactionCollection;

public class FormViewTransactions {

	JFrame frmViewTransactions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormViewTransactions window = new FormViewTransactions((Integer)null);
					window.frmViewTransactions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public FormViewTransactions(Integer customerId) throws SQLException {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(Integer customerId) throws SQLException {
		ArrayList<Integer> accounts = Account.searchByCustomerId(customerId);
		frmViewTransactions = new JFrame();
		frmViewTransactions.setTitle("Menu View Transactions");
		frmViewTransactions.setBounds(100, 100, 450, 300);
		frmViewTransactions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewTransactions.getContentPane().setLayout(null);
		
		Integer[] array = new Integer[accounts.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] = accounts.get(i);
		}
		
		JComboBox comboBoxAccount = new JComboBox(array);
		comboBoxAccount.setBounds(109, 65, 111, 21);
		frmViewTransactions.getContentPane().add(comboBoxAccount);
		
		JComboBox comboBoxSort = new JComboBox();
		comboBoxSort.setModel(new DefaultComboBoxModel(new String[] {"Date", "Amount"}));
		comboBoxSort.setBounds(109, 101, 111, 21);
		frmViewTransactions.getContentPane().add(comboBoxSort);
		
		JButton btnViewTransactions = new JButton("View Transactions");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer accountId = (Integer) comboBoxAccount.getSelectedItem();
					String sortBy = (String) comboBoxSort.getSelectedItem();
					
					ArrayList<Transaction> transactions = Transaction.searchByAccount(accountId);
					if (sortBy.equals("Date")) {
						TransactionCollection.sortByDate(transactions, new PredicateDate());
						FormShowTransactions formShowTransactions = new FormShowTransactions(transactions);
						formShowTransactions.frmShowTransactions.setVisible(true);
						
					} 
					else if (sortBy.equals("Amount")) {
						TransactionCollection.sortByAmount(transactions, new PredicateAmount());
						FormShowTransactions formShowTransactions = new FormShowTransactions(transactions);
						formShowTransactions.frmShowTransactions.setVisible(true);
					}
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
				
				
				
				
				
			}
		});
		btnViewTransactions.setBounds(321, 232, 85, 21);
		frmViewTransactions.getContentPane().add(btnViewTransactions);
		
		JButton btnExit = new JButton("Cancel");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				frmViewTransactions.dispose();
			}
		});
		btnExit.setBounds(210, 232, 85, 21);
		frmViewTransactions.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Sort by:");
		lblNewLabel.setBounds(54, 105, 45, 13);
		frmViewTransactions.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account:");
		lblNewLabel_1.setBounds(54, 69, 45, 13);
		frmViewTransactions.getContentPane().add(lblNewLabel_1);
		

	}
}

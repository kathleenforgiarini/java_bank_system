package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CreditAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.LineOfCreditAccount;
import bus.Manager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class FormOpenLineOfCreditAccount {

	JFrame frmOpenLineOfCreditAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldLimit;
	private JTextField textFieldInterestRate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenLineOfCreditAccount window = new FormOpenLineOfCreditAccount((Integer)null);
					window.frmOpenLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenLineOfCreditAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenLineOfCreditAccount = new JFrame();
		frmOpenLineOfCreditAccount.setTitle("Open Line Of Credit Account");
		frmOpenLineOfCreditAccount.setBounds(100, 100, 450, 310);
		frmOpenLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblUserid = new JLabel("Select customer user id:");
		lblUserid.setBounds(23, 28, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblUserid);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 22, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 220, 113, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 214, 225, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldPassword);
		
		JLabel lblDueDay = new JLabel("Insert the Due Date Day:");
		lblDueDay.setBounds(23, 57, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDay);
		
		JSpinner spinnerDay = new JSpinner();
		spinnerDay.setBounds(211, 51, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerDay);
		
		JSpinner spinnerMonth = new JSpinner();
		spinnerMonth.setBounds(211, 83, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerMonth);
		
		JLabel lblDueDateMonth = new JLabel("Insert the Due Date Month:");
		lblDueDateMonth.setBounds(23, 86, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDateMonth);
		
		JLabel lblDueDateYear = new JLabel("Insert the Due Date Year:");
		lblDueDateYear.setBounds(23, 114, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDateYear);
		
		JSpinner spinnerYear = new JSpinner();
		spinnerYear.setBounds(211, 111, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerYear);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					
					Integer day = (Integer) spinnerDay.getValue();
					Integer month = (Integer) spinnerMonth.getValue();
					Integer year = (Integer) spinnerYear.getValue();				
					LocalDate dueDate = LocalDate.of(year, month, day);
					
					Double limit = Double.parseDouble(textFieldLimit.getText());
					
					Double interestRate = Double.parseDouble(textFieldInterestRate.getText());
					
					Customer selectedCustomer = Customer.search(selectedId);
					
					Manager.openLineOfCreditAccount(selectedCustomer.getId(), dueDate, limit, interestRate);

					JOptionPane.showMessageDialog(null, "Line of Credit Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenLineOfCreditAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 243, 138, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenLineOfCreditAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 243, 77, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnCancel);
		
		JLabel lblLimit = new JLabel("Insert the Limit:");
		lblLimit.setBounds(23, 147, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblLimit);
		
		textFieldLimit = new JTextField();
		textFieldLimit.setColumns(10);
		textFieldLimit.setBounds(211, 141, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldLimit);
		
		textFieldInterestRate = new JTextField();
		textFieldInterestRate.setColumns(10);
		textFieldInterestRate.setBounds(211, 170, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldInterestRate);
		
		JLabel lblInterestRate = new JLabel("Insert the Interest Rate:");
		lblInterestRate.setBounds(23, 176, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblInterestRate);
	}
}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CreditAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.Manager;
import bus.SavingAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class FormOpenCreditAccount {

	JFrame frmOpenCreditAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldLimit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenCreditAccount window = new FormOpenCreditAccount((Integer)null);
					window.frmOpenCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenCreditAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenCreditAccount = new JFrame();
		frmOpenCreditAccount.setTitle("Open Credit Account");
		frmOpenCreditAccount.setBounds(100, 100, 450, 314);
		frmOpenCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("Select customer user Id:");
		lblUserId.setBounds(23, 28, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 22, 186, 19);
		frmOpenCreditAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 222, 113, 13);
		frmOpenCreditAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 216, 225, 19);
		frmOpenCreditAccount.getContentPane().add(textFieldPassword);
		
		JSpinner spinnerDay = new JSpinner();
		spinnerDay.setBounds(211, 80, 186, 20);
		frmOpenCreditAccount.getContentPane().add(spinnerDay);
		
		JSpinner spinnerMonth = new JSpinner();
		spinnerMonth.setBounds(211, 112, 186, 20);
		frmOpenCreditAccount.getContentPane().add(spinnerMonth);
		
		JSpinner spinnerYear = new JSpinner();
		spinnerYear.setBounds(211, 140, 186, 20);
		frmOpenCreditAccount.getContentPane().add(spinnerYear);
		
		JLabel lblDueDateYear = new JLabel("Insert the Due Date Year:");
		lblDueDateYear.setBounds(23, 143, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblDueDateYear);
		
		JLabel lblDueDateMonth = new JLabel("Insert the Due Date Month:");
		lblDueDateMonth.setBounds(23, 115, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblDueDateMonth);
		
		JLabel lblDueDay = new JLabel("Insert the Due Date Day:");
		lblDueDay.setBounds(23, 86, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblDueDay);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					Double balance = Double.parseDouble(textFieldBalance.getText());
					
					Integer day = (Integer) spinnerDay.getValue();
					Integer month = (Integer) spinnerMonth.getValue();
					Integer year = (Integer) spinnerYear.getValue();
					
					Double limit = Double.parseDouble(textFieldLimit.getText());
					
					LocalDate dueDate = LocalDate.of(year, month, day);
					
					Customer selectedCustomer = Customer.search(selectedId);
					
					Manager.openCreditAccount(selectedCustomer.getId(), balance, dueDate, limit);

					JOptionPane.showMessageDialog(null, "Credit Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenCreditAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 245, 138, 21);
		frmOpenCreditAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenCreditAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 245, 77, 21);
		frmOpenCreditAccount.getContentPane().add(btnCancel);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(211, 51, 186, 19);
		frmOpenCreditAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Insert the initial Balance:");
		lblBalance.setBounds(23, 57, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblBalance);
		
		JLabel lblLimit = new JLabel("Insert the Limit:");
		lblLimit.setBounds(23, 176, 139, 13);
		frmOpenCreditAccount.getContentPane().add(lblLimit);
		
		textFieldLimit = new JTextField();
		textFieldLimit.setColumns(10);
		textFieldLimit.setBounds(211, 170, 186, 19);
		frmOpenCreditAccount.getContentPane().add(textFieldLimit);		
	}
}

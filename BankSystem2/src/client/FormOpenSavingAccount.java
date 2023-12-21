package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CheckingAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.ExceptionIsPassedDate;
import bus.Manager;
import bus.SavingAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class FormOpenSavingAccount {

	JFrame frmOpenSavingAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldInterestRate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenSavingAccount window = new FormOpenSavingAccount((Integer)null);
					window.frmOpenSavingAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenSavingAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenSavingAccount = new JFrame();
		frmOpenSavingAccount.setTitle("Open Saving Account");
		frmOpenSavingAccount.setBounds(100, 100, 450, 328);
		frmOpenSavingAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenSavingAccount.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("Select customer user id:");
		lblUserId.setBounds(23, 28, 139, 13);
		frmOpenSavingAccount.getContentPane().add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 22, 186, 19);
		frmOpenSavingAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 237, 113, 13);
		frmOpenSavingAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 231, 225, 19);
		frmOpenSavingAccount.getContentPane().add(textFieldPassword);
		
		JSpinner spinnerDay = new JSpinner();
		spinnerDay.setBounds(211, 109, 186, 20);
		frmOpenSavingAccount.getContentPane().add(spinnerDay);
		
		JSpinner spinnerMonth = new JSpinner();
		spinnerMonth.setBounds(211, 141, 186, 20);
		frmOpenSavingAccount.getContentPane().add(spinnerMonth);
		
		JSpinner spinnerYear = new JSpinner();
		spinnerYear.setBounds(211, 169, 186, 20);
		frmOpenSavingAccount.getContentPane().add(spinnerYear);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					Double balance = Double.parseDouble(textFieldBalance.getText());
					Double intRate = Double.parseDouble(textFieldInterestRate.getText());
					intRate = intRate/100;
					Integer day = (Integer) spinnerDay.getValue();
					Integer month = (Integer) spinnerMonth.getValue();
					Integer year = (Integer) spinnerYear.getValue();
					
					LocalDate dueDate = LocalDate.of(year, month, day);
					
					LocalDate now = LocalDate.now();
					if (dueDate.isBefore(now)) {
						throw new ExceptionIsPassedDate();
					}
					
					Customer selectedCustomer = Customer.search(selectedId);
					
					Manager.openSavingAccount(selectedCustomer.getId(), balance, intRate, dueDate);

					JOptionPane.showMessageDialog(null, "Saving Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenSavingAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 260, 138, 21);
		frmOpenSavingAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenSavingAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 260, 77, 21);
		frmOpenSavingAccount.getContentPane().add(btnCancel);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(211, 51, 186, 19);
		frmOpenSavingAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Insert the initial Balance:");
		lblBalance.setBounds(23, 57, 139, 13);
		frmOpenSavingAccount.getContentPane().add(lblBalance);
		
		JLabel lblInterestRate = new JLabel("Insert the Interest Rate:");
		lblInterestRate.setBounds(23, 86, 178, 13);
		frmOpenSavingAccount.getContentPane().add(lblInterestRate);
		
		textFieldInterestRate = new JTextField();
		textFieldInterestRate.setColumns(10);
		textFieldInterestRate.setBounds(211, 80, 186, 19);
		frmOpenSavingAccount.getContentPane().add(textFieldInterestRate);
		
		JLabel lblDueDay = new JLabel("Insert the Due Date Day:");
		lblDueDay.setBounds(23, 115, 139, 13);
		frmOpenSavingAccount.getContentPane().add(lblDueDay);
		
		JLabel lblDueDateMonth = new JLabel("Insert the Due Date Month:");
		lblDueDateMonth.setBounds(23, 144, 139, 13);
		frmOpenSavingAccount.getContentPane().add(lblDueDateMonth);
		
		JLabel lblDueDateYear = new JLabel("Insert the Due Date Year:");
		lblDueDateYear.setBounds(23, 172, 139, 13);
		frmOpenSavingAccount.getContentPane().add(lblDueDateYear);
	}
}

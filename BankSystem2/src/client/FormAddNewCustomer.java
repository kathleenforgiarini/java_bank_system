package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CheckingAccount;
import bus.Customer;
import bus.EnumTypeAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class FormAddNewCustomer {

	JFrame frmAddNewCustomer;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldSalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAddNewCustomer window = new FormAddNewCustomer((Integer)null);
					window.frmAddNewCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormAddNewCustomer(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmAddNewCustomer = new JFrame();
		frmAddNewCustomer.setTitle("Add New Customer");
		frmAddNewCustomer.setBounds(100, 100, 450, 300);
		frmAddNewCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewCustomer.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(23, 28, 113, 13);
		frmAddNewCustomer.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(172, 22, 225, 19);
		frmAddNewCustomer.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(23, 80, 113, 13);
		frmAddNewCustomer.getContentPane().add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 74, 225, 19);
		frmAddNewCustomer.getContentPane().add(textFieldPassword);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(23, 54, 113, 13);
		frmAddNewCustomer.getContentPane().add(lblSalary);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setColumns(10);
		textFieldSalary.setBounds(172, 48, 225, 19);
		frmAddNewCustomer.getContentPane().add(textFieldSalary);
		
		JButton btnCreateCustomer = new JButton("Create Customer");
		Customer newCustomer = new Customer();
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textFieldUsername.getText();
				String salary = textFieldSalary.getText();
				String password = textFieldPassword.getText();
				
				if (username.isEmpty() || salary.isEmpty() || password.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
				}
				else
				{
					try {
						Integer passwordParsed = Integer.parseInt(password);
						Double salaryParsed = Double.parseDouble(salary);
						
						newCustomer.setUsername(username);
						newCustomer.setPassword(passwordParsed);
						newCustomer.setSalary(salaryParsed);
						newCustomer.setMgr(mgrId);
						Integer addedId = Customer.add(newCustomer);
						
						JOptionPane.showMessageDialog(null, "User Created!");
						
						CheckingAccount newCheckingAccount = new CheckingAccount((Integer)null, EnumTypeAccount.CheckingAccount, addedId, 0.00, LocalDate.now(), 3, 5.00);
						CheckingAccount.add(newCheckingAccount);
						
						JOptionPane.showMessageDialog(null, "Checking Account Created!");

						FormMenuManager formMenuManager = new FormMenuManager(mgrId);
						formMenuManager.frmHomeManager.setVisible(true);
						
						frmAddNewCustomer.dispose();
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, exc.getMessage());
					}
				}
			}
		});
		btnCreateCustomer.setBounds(259, 218, 138, 21);
		frmAddNewCustomer.getContentPane().add(btnCreateCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmAddNewCustomer.dispose();
			}
		});
		btnCancel.setBounds(172, 218, 77, 21);
		frmAddNewCustomer.getContentPane().add(btnCancel);
	}
}

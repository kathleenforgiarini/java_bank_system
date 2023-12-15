package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CheckingAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.Manager;
import bus.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

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
		frmAddNewCustomer.setBounds(100, 100, 412, 300);
		frmAddNewCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewCustomer.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(10, 63, 97, 13);
		frmAddNewCustomer.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(143, 57, 225, 19);
		frmAddNewCustomer.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(10, 132, 97, 13);
		frmAddNewCustomer.getContentPane().add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(143, 126, 225, 19);
		frmAddNewCustomer.getContentPane().add(textFieldPassword);
		
		JLabel lblSalary = new JLabel("Salary CAD$");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalary.setBounds(10, 96, 97, 13);
		frmAddNewCustomer.getContentPane().add(lblSalary);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setColumns(10);
		textFieldSalary.setBounds(143, 90, 225, 19);
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
						
						try {
							Manager.createCustomer(username, passwordParsed, salaryParsed, mgrId);
							JOptionPane.showMessageDialog(null, "Customer Created!");
							JOptionPane.showMessageDialog(null, "Checking Account Created!");

							FormMenuManager formMenuManager = new FormMenuManager(mgrId);
							formMenuManager.frmHomeManager.setVisible(true);
							
							frmAddNewCustomer.dispose();
						}
						catch(Exception exc) {
							JOptionPane.showMessageDialog(null, "DB Exception: \nThis username already exists.");
						}
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, "The salary and the password must be a number");
					}
				}
			}
		});
		btnCreateCustomer.setBounds(248, 217, 138, 21);
		frmAddNewCustomer.getContentPane().add(btnCreateCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
			//	FormMenuManager formMenuManagerback = new FormMenuManager(mgrFound.getId());
				formMenuManager.frmHomeManager.setVisible(true);
				
				  JLabel lblHelloLabel = new JLabel("Chose your next operation");
				  lblHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
				  lblHelloLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  lblHelloLabel.setBounds(101, 26, 218, 14);
				  formMenuManager.frmHomeManager.getContentPane().add(lblHelloLabel);
				
				frmAddNewCustomer.dispose();
			}
		});
		btnCancel.setBounds(161, 217, 77, 21);
		frmAddNewCustomer.getContentPane().add(btnCancel);
		
		JLabel lblInformation = new JLabel("Enter the new customer information");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformation.setBounds(75, 11, 252, 19);
		frmAddNewCustomer.getContentPane().add(lblInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmAddNewCustomer.getContentPane().add(lblNewLabel);
		
	}
}

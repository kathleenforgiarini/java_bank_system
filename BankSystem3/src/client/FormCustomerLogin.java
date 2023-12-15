package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Customer;
import bus.Manager;
import bus.User;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class FormCustomerLogin {

	JFrame frmCustomerAreaLogin;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCustomerLogin window = new FormCustomerLogin();
					window.frmCustomerAreaLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormCustomerLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustomerAreaLogin = new JFrame();
		frmCustomerAreaLogin.setTitle("Customer Area Login");
		frmCustomerAreaLogin.setBounds(100, 100, 380, 300);
		frmCustomerAreaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerAreaLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(50, 53, 105, 13);
		frmCustomerAreaLogin.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(150, 50, 142, 19);
		frmCustomerAreaLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(150, 108, 142, 19);
		frmCustomerAreaLogin.getContentPane().add(textFieldPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 111, 105, 13);
		frmCustomerAreaLogin.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = textFieldUsername.getText();
					Integer password = Integer.parseInt(textFieldPassword.getText());
					
					if (username.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Please, enter both fields");
					}
					else
					{
						try {
							User userFound = User.search(username, password);
							Customer customerFound = Customer.search(userFound.getId());
							if (customerFound == null)
							{
								JOptionPane.showMessageDialog(null, "Invalid Credentials!");
							}
							else
							{
								FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerFound.getId());
								formMenuCustomer.frmHomeCustomer.setVisible(true);
								
								 JLabel lblHelloLabel = new JLabel("Hello, "+username);
								  lblHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
								  lblHelloLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
								  lblHelloLabel.setBounds(101, 26, 218, 14);
								  formMenuCustomer.frmHomeCustomer.getContentPane().add(lblHelloLabel);

								frmCustomerAreaLogin.dispose();
							}
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(null, "The user and password do not match.");
						}
					}
				} catch (Exception exc)
				{
					JOptionPane.showMessageDialog(null, "The fields must not be empty.\nThe password must be a number.");
				}
				
				
				
			}
		});
		btnLogin.setBounds(207, 165, 85, 21);
		frmCustomerAreaLogin.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmCustomerAreaLogin.dispose();
			}
		});
		btnExit.setBounds(207, 200, 85, 21);
		frmCustomerAreaLogin.getContentPane().add(btnExit);
		
		JLabel lblInformation = new JLabel("Enter your Customer Credentials");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(50, 11, 242, 14);
		frmCustomerAreaLogin.getContentPane().add(lblInformation);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmCustomerAreaLogin.getContentPane().add(lblNewLabel);
	}

}

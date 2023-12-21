package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Customer;
import bus.Manager;
import bus.User;

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
		frmCustomerAreaLogin.setBounds(100, 100, 450, 300);
		frmCustomerAreaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerAreaLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(61, 53, 105, 13);
		frmCustomerAreaLogin.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(228, 50, 142, 19);
		frmCustomerAreaLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(228, 108, 142, 19);
		frmCustomerAreaLogin.getContentPane().add(textFieldPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(61, 111, 105, 13);
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
							
							frmCustomerAreaLogin.dispose();
						}
					}
				} catch (Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
				
				
			}
		});
		btnLogin.setBounds(285, 197, 85, 21);
		frmCustomerAreaLogin.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmCustomerAreaLogin.dispose();
			}
		});
		btnExit.setBounds(190, 197, 85, 21);
		frmCustomerAreaLogin.getContentPane().add(btnExit);
	}

}

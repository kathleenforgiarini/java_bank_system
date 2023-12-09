package client;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		btnLogin.setBounds(285, 197, 85, 21);
		frmCustomerAreaLogin.getContentPane().add(btnLogin);
	}

}

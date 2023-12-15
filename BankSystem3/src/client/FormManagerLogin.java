package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.ExceptionIsNotANumber;
import bus.Manager;
import bus.User;
import bus.Validator;
import data.ManagerDB;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class FormManagerLogin {

	JFrame frmManagerAreaLogin;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormManagerLogin window = new FormManagerLogin();
					window.frmManagerAreaLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormManagerLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagerAreaLogin = new JFrame();
		frmManagerAreaLogin.setTitle("Manager Area Login");
		frmManagerAreaLogin.setBounds(100, 100, 380, 300);
		frmManagerAreaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagerAreaLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(50, 53, 105, 13);
		frmManagerAreaLogin.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(150, 50, 142, 19);
		frmManagerAreaLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(150, 108, 142, 19);
		frmManagerAreaLogin.getContentPane().add(textFieldPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 111, 105, 13);
		frmManagerAreaLogin.getContentPane().add(lblPassword);
		
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
							Manager mgrFound = Manager.search(userFound.getId());
							if (mgrFound == null)
							{
								JOptionPane.showMessageDialog(null, "Invalid Credentials!");
							}
							else
							{
								FormMenuManager formMenuManager = new FormMenuManager(mgrFound.getId());
								formMenuManager.frmHomeManager.setVisible(true);
								
								  JLabel lblHelloLabel = new JLabel("Hello, "+username);
								  lblHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
								  lblHelloLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
								  lblHelloLabel.setBounds(101, 26, 218, 14);
								  formMenuManager.frmHomeManager.getContentPane().add(lblHelloLabel);
								  frmManagerAreaLogin.dispose();
								 
							}
						} catch (Exception exc)
						{
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
		frmManagerAreaLogin.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmManagerAreaLogin.dispose();
			}
		});
		btnExit.setBounds(207, 200, 85, 21);
		frmManagerAreaLogin.getContentPane().add(btnExit);
		
		JLabel lblInstructions = new JLabel("Enter your Manager Credentials");
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions.setForeground(new Color(0, 0, 0));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(50, 11, 242, 14);
		frmManagerAreaLogin.getContentPane().add(lblInstructions);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmManagerAreaLogin.getContentPane().add(lblNewLabel);
		
		
	}
}

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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frmManagerAreaLogin.setBounds(100, 100, 450, 300);
		frmManagerAreaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagerAreaLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(61, 53, 105, 13);
		frmManagerAreaLogin.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(228, 50, 142, 19);
		frmManagerAreaLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(228, 108, 142, 19);
		frmManagerAreaLogin.getContentPane().add(textFieldPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(61, 111, 105, 13);
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
						User userFound = User.search(username, password);
						//JOptionPane.showMessageDialog(null, "The id of the user is " + userFound.getId());
						Manager mgrFound = Manager.search(userFound.getId());
						if (mgrFound == null)
						{
							JOptionPane.showMessageDialog(null, "Invalid Credentials!");
						}
						else
						{
							FormMenuManager formMenuManager = new FormMenuManager(mgrFound.getId());
							formMenuManager.frmHomeManager.setVisible(true);
							
							frmManagerAreaLogin.dispose();
						}
					}
				} catch (Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnLogin.setBounds(285, 197, 85, 21);
		frmManagerAreaLogin.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmManagerAreaLogin.dispose();
			}
		});
		btnExit.setBounds(190, 197, 85, 21);
		frmManagerAreaLogin.getContentPane().add(btnExit);
	}
}

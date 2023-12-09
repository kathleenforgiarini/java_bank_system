package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormLogin {

	JFrame frmWelcomeToFortis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin window = new FormLogin();
					window.frmWelcomeToFortis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeToFortis = new JFrame();
		frmWelcomeToFortis.setTitle("Welcome to Fortis Bank");
		frmWelcomeToFortis.setBounds(100, 100, 450, 300);
		frmWelcomeToFortis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeToFortis.getContentPane().setLayout(null);
		
		JButton btnLoginManager = new JButton("Manager Area");
		btnLoginManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormManagerLogin formMgrLogin = new FormManagerLogin();
				formMgrLogin.frmManagerAreaLogin.setVisible(true);
				
				frmWelcomeToFortis.dispose();
			}
		});
		btnLoginManager.setBounds(67, 63, 127, 38);
		frmWelcomeToFortis.getContentPane().add(btnLoginManager);
		
		JButton btnLoginCustomer = new JButton("Customer Area");
		btnLoginCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCustomerLogin formCustomerLogin = new FormCustomerLogin();
				formCustomerLogin.frmCustomerAreaLogin.setVisible(true);
				
				frmWelcomeToFortis.dispose();
			}
		});
		btnLoginCustomer.setBounds(222, 63, 127, 38);
		frmWelcomeToFortis.getContentPane().add(btnLoginCustomer);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(264, 175, 85, 21);
		frmWelcomeToFortis.getContentPane().add(btnExit);
	}
}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuOpenAccount {

	JFrame frmHomeNewAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuOpenAccount window = new FormMenuOpenAccount();
					window.frmHomeNewAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuOpenAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomeNewAccount = new JFrame();
		frmHomeNewAccount.setTitle("Home New Account");
		frmHomeNewAccount.setBounds(100, 100, 450, 300);
		frmHomeNewAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeNewAccount.getContentPane().setLayout(null);
		
		JButton btnCheckingAccount = new JButton("Add New Checking Account");
		btnCheckingAccount.setBounds(117, 34, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCheckingAccount);
		
		JButton btnSavingsAccount = new JButton("Add New Savings Account");
		btnSavingsAccount.setBounds(117, 65, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnSavingsAccount);
		
		JButton btnCurrencyAccount = new JButton("Add New Currency Account");
		btnCurrencyAccount.setBounds(117, 96, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCurrencyAccount);
		
		JButton btnCreditAccount = new JButton("Add New Credit Account");
		btnCreditAccount.setBounds(117, 127, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCreditAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager();
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeNewAccount.getContentPane().add(btnExit);
		
		JButton btnLineOfCreditAccount = new JButton("Add New Line Of Credit Account");
		btnLineOfCreditAccount.setBounds(117, 158, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnLineOfCreditAccount);
	}

}

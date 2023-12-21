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
					FormMenuOpenAccount window = new FormMenuOpenAccount((Integer)null);
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
	public FormMenuOpenAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmHomeNewAccount = new JFrame();
		frmHomeNewAccount.setTitle("Home New Account");
		frmHomeNewAccount.setBounds(100, 100, 450, 300);
		frmHomeNewAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeNewAccount.getContentPane().setLayout(null);
		
		JButton btnCheckingAccount = new JButton("Add New Checking Account");
		btnCheckingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCheckingAccount formOpenCheckAccount = new FormOpenCheckingAccount(mgrId);
				formOpenCheckAccount.frmOpenCheckingAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCheckingAccount.setBounds(117, 34, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCheckingAccount);
		
		JButton btnSavingsAccount = new JButton("Add New Savings Account");
		btnSavingsAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenSavingAccount formOpenSaveAccount = new FormOpenSavingAccount(mgrId);
				formOpenSaveAccount.frmOpenSavingAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnSavingsAccount.setBounds(117, 65, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnSavingsAccount);
		
		JButton btnCurrencyAccount = new JButton("Add New Currency Account");
		btnCurrencyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCurrencyAccount formOpenCurrencyAccount = new FormOpenCurrencyAccount(mgrId);
				formOpenCurrencyAccount.frmOpenCurrencyAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCurrencyAccount.setBounds(117, 96, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCurrencyAccount);
		
		JButton btnCreditAccount = new JButton("Add New Credit Account");
		btnCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCreditAccount formOpenCreditAccount = new FormOpenCreditAccount(mgrId);
				formOpenCreditAccount.frmOpenCreditAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCreditAccount.setBounds(117, 127, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCreditAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeNewAccount.getContentPane().add(btnExit);
		
		JButton btnLineOfCreditAccount = new JButton("Add New Line Of Credit Account");
		btnLineOfCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenLineOfCreditAccount formOpenLineOfCreditAccount = new FormOpenLineOfCreditAccount(mgrId);
				formOpenLineOfCreditAccount.frmOpenLineOfCreditAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnLineOfCreditAccount.setBounds(117, 158, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnLineOfCreditAccount);
	}

}

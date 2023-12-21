package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuDeposit {

	JFrame frmMenuDeposit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuDeposit window = new FormMenuDeposit((Integer)null);
					window.frmMenuDeposit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuDeposit(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmMenuDeposit = new JFrame();
		frmMenuDeposit.setTitle("Home New Deposit");
		frmMenuDeposit.setBounds(100, 100, 459, 274);
		frmMenuDeposit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuDeposit.getContentPane().setLayout(null);
		
		JButton btnDepositIntoChecking = new JButton("Deposit Into Checking Account");
		btnDepositIntoChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepositCheckingAccount formDepositCheckingAccount = new FormDepositCheckingAccount(customerId);
				formDepositCheckingAccount.frmDepositCheckingAccount.setVisible(true);
				frmMenuDeposit.dispose();
			}
		});
		btnDepositIntoChecking.setBounds(127, 26, 192, 21);
		frmMenuDeposit.getContentPane().add(btnDepositIntoChecking);
		
		JButton btnDepositIntoSaving = new JButton("Deposit Into Saving Account");
		btnDepositIntoSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepositSavingAccount formDepositSavingAccount = new FormDepositSavingAccount(customerId);
				formDepositSavingAccount.frmDepositSavingAccount.setVisible(true);
				frmMenuDeposit.dispose();
			}
		});
		btnDepositIntoSaving.setBounds(127, 57, 192, 21);
		frmMenuDeposit.getContentPane().add(btnDepositIntoSaving);
		
		JButton btnDepositIntoCurrencyAccount = new JButton("Deposit Into Currency Account");
		btnDepositIntoCurrencyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepositCurrencyAccount formDepositCurrencyAccount = new FormDepositCurrencyAccount(customerId);
				formDepositCurrencyAccount.frmDepositCurrencyAccount.setVisible(true);
				frmMenuDeposit.dispose();	
			}
		});
		btnDepositIntoCurrencyAccount.setBounds(127, 88, 192, 21);
		frmMenuDeposit.getContentPane().add(btnDepositIntoCurrencyAccount);
		
		JButton btnDepositIntoCredit = new JButton("Deposit Into Credit Account");
		btnDepositIntoCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepositCreditAccount formDepositCreditAccount = new FormDepositCreditAccount(customerId);
				formDepositCreditAccount.frmDepositCreditAccount.setVisible(true);
				frmMenuDeposit.dispose();
			}
		});
		btnDepositIntoCredit.setBounds(127, 119, 192, 21);
		frmMenuDeposit.getContentPane().add(btnDepositIntoCredit);
		
		JButton btnDepositIntoLine = new JButton("Deposit Into Line Of Credit Account");
		btnDepositIntoLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepositLineOfCreditAccount formDepositLineOfCreditAccount = new FormDepositLineOfCreditAccount(customerId);
				formDepositLineOfCreditAccount.frmDepositLineOfCreditAccount.setVisible(true);
				frmMenuDeposit.dispose();
			}
		});
		btnDepositIntoLine.setBounds(127, 150, 192, 21);
		frmMenuDeposit.getContentPane().add(btnDepositIntoLine);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				frmMenuDeposit.dispose();
			}
		});
		btnExit.setBounds(322, 194, 85, 21);
		frmMenuDeposit.getContentPane().add(btnExit);
	}

}

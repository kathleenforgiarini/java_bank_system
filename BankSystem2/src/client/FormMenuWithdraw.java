package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuWithdraw {

	JFrame frmMenuWithdraw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuWithdraw window = new FormMenuWithdraw((Integer)null);
					window.frmMenuWithdraw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuWithdraw(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmMenuWithdraw = new JFrame();
		frmMenuWithdraw.setTitle("Home New Withdraw");
		frmMenuWithdraw.setBounds(100, 100, 451, 259);
		frmMenuWithdraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuWithdraw.getContentPane().setLayout(null);
		
		JButton btnWithdrawFromSaving = new JButton("Withdraw From Saving Account");
		btnWithdrawFromSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawSavingAccount formWithdrawSavingAccount = new FormWithdrawSavingAccount(customerId);
				formWithdrawSavingAccount.frmWithdrawSavingAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromSaving.setBounds(117, 67, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromSaving);
		
		JButton btnWithdrawFromChecking = new JButton("Withdraw From Checking Account");
		btnWithdrawFromChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCheckingAccount formWithdrawCheckingAccount = new FormWithdrawCheckingAccount(customerId);
				formWithdrawCheckingAccount.frmWithdrawCheckingAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromChecking.setBounds(117, 36, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromChecking);
		
		JButton btnWithdrawFromCurrencyAccount = new JButton("Withdraw From Currency Account");
		btnWithdrawFromCurrencyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCurrencyAccount formWithdrawCurrencyAccount = new FormWithdrawCurrencyAccount(customerId);
				formWithdrawCurrencyAccount.frmWithdrawCurrencyAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromCurrencyAccount.setBounds(117, 98, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromCurrencyAccount);
		
		JButton btnWithdrawFromCredit = new JButton("Withdraw From Credit Account");
		btnWithdrawFromCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCreditAccount formWithdrawCreditAccount = new FormWithdrawCreditAccount(customerId);
				formWithdrawCreditAccount.frmWithdrawCreditAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromCredit.setBounds(117, 129, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromCredit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnExit.setBounds(306, 185, 85, 21);
		frmMenuWithdraw.getContentPane().add(btnExit);
	}

}

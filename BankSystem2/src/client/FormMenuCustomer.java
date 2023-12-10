package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuCustomer {

	JFrame frmHomeCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuCustomer window = new FormMenuCustomer((Integer)null);
					window.frmHomeCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuCustomer(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmHomeCustomer = new JFrame();
		frmHomeCustomer.setTitle("Home Customer");
		frmHomeCustomer.setBounds(100, 100, 450, 300);
		frmHomeCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeCustomer.getContentPane().setLayout(null);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuDeposit frmMenuDeposit = new FormMenuDeposit(customerId);
				frmMenuDeposit.frmMenuDeposit.setVisible(true);
				
				frmHomeCustomer.dispose();
				
			}
		});
		btnDeposit.setBounds(145, 53, 129, 21);
		frmHomeCustomer.getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuWithdraw frmMenuWithdraw = new FormMenuWithdraw(customerId);
				frmMenuWithdraw.frmMenuWithdraw.setVisible(true);
				
				frmHomeCustomer.dispose();
			}
		});
		btnWithdraw.setBounds(145, 84, 129, 21);
		frmHomeCustomer.getContentPane().add(btnWithdraw);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuTransfer formMenuTransfer = new FormMenuTransfer(customerId);
				formMenuTransfer.frmMenuTransfer.setVisible(true);
				
				frmHomeCustomer.dispose();
			}
		});
		btnTransfer.setBounds(145, 115, 129, 21);
		frmHomeCustomer.getContentPane().add(btnTransfer);
		
		JButton btnGetBalance = new JButton("Get Balance");
		btnGetBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormGetBalance formGetBalance = new FormGetBalance(customerId);
				formGetBalance.frmGetBalance.setVisible(true);
				
				frmHomeCustomer.dispose();
			}
		});
		btnGetBalance.setBounds(145, 146, 129, 21);
		frmHomeCustomer.getContentPane().add(btnGetBalance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmHomeCustomer.dispose();
			}
		});
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeCustomer.getContentPane().add(btnExit);
	}

}

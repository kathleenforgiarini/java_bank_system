package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

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
		
		JButton btnWithdrawFromSaving = new JButton("Saving Account");
		btnWithdrawFromSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawSavingAccount formWithdrawSavingAccount = new FormWithdrawSavingAccount(customerId);
				formWithdrawSavingAccount.frmWithdrawSavingAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromSaving.setBounds(117, 91, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromSaving);
		
		JButton btnWithdrawFromChecking = new JButton("Checking Account");
		btnWithdrawFromChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCheckingAccount formWithdrawCheckingAccount = new FormWithdrawCheckingAccount(customerId);
				formWithdrawCheckingAccount.frmWithdrawCheckingAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromChecking.setBounds(117, 60, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromChecking);
		
		JButton btnWithdrawFromCurrencyAccount = new JButton("Currency Account");
		btnWithdrawFromCurrencyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCurrencyAccount formWithdrawCurrencyAccount = new FormWithdrawCurrencyAccount(customerId);
				formWithdrawCurrencyAccount.frmWithdrawCurrencyAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromCurrencyAccount.setBounds(117, 122, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromCurrencyAccount);
		
		JButton btnWithdrawFromCredit = new JButton("Credit Account");
		btnWithdrawFromCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormWithdrawCreditAccount formWithdrawCreditAccount = new FormWithdrawCreditAccount(customerId);
				formWithdrawCreditAccount.frmWithdrawCreditAccount.setVisible(true);
				frmMenuWithdraw.dispose();
			}
		});
		btnWithdrawFromCredit.setBounds(117, 153, 192, 21);
		frmMenuWithdraw.getContentPane().add(btnWithdrawFromCredit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				
				JLabel lblInformation = new JLabel("Choose which account you want to deposit into");
				lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
				lblInformation.setBounds(10, 11, 423, 21);
				formMenuCustomer.frmHomeCustomer.getContentPane().add(lblInformation);
				
				
				frmMenuWithdraw.dispose();
			}
		});
		btnExit.setBounds(306, 185, 85, 21);
		frmMenuWithdraw.getContentPane().add(btnExit);
		
		JLabel lblInformation = new JLabel("Choose which account you want to withdraw");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(10, 11, 415, 21);
		frmMenuWithdraw.getContentPane().add(lblInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 115, 107, 105);
		frmMenuWithdraw.getContentPane().add(lblNewLabel);
	}
}

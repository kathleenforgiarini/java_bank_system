package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

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
		
		JButton btnCheckingAccount = new JButton("Checking Account");
		btnCheckingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCheckingAccount formOpenCheckAccount = new FormOpenCheckingAccount(mgrId);
				formOpenCheckAccount.frmOpenCheckingAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCheckingAccount.setBounds(117, 52, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCheckingAccount);
		
		JButton btnSavingsAccount = new JButton("Savings Account");
		btnSavingsAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenSavingAccount formOpenSaveAccount = new FormOpenSavingAccount(mgrId);
				formOpenSaveAccount.frmOpenSavingAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnSavingsAccount.setBounds(117, 83, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnSavingsAccount);
		
		JButton btnCurrencyAccount = new JButton("Currency Account");
		btnCurrencyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCurrencyAccount formOpenCurrencyAccount = new FormOpenCurrencyAccount(mgrId);
				formOpenCurrencyAccount.frmOpenCurrencyAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCurrencyAccount.setBounds(117, 114, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCurrencyAccount);
		
		JButton btnCreditAccount = new JButton("Credit Account");
		btnCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenCreditAccount formOpenCreditAccount = new FormOpenCreditAccount(mgrId);
				formOpenCreditAccount.frmOpenCreditAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnCreditAccount.setBounds(117, 145, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnCreditAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				JLabel lblHelloLabel = new JLabel("Chose your next operation");
				  lblHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
				  lblHelloLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  lblHelloLabel.setBounds(101, 26, 218, 14);
				  formMenuManager.frmHomeManager.getContentPane().add(lblHelloLabel);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnExit.setBounds(309, 229, 85, 21);
		frmHomeNewAccount.getContentPane().add(btnExit);
		
		JButton btnLineOfCreditAccount = new JButton("Line Of Credit Account");
		btnLineOfCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormOpenLineOfCreditAccount formOpenLineOfCreditAccount = new FormOpenLineOfCreditAccount(mgrId);
				formOpenLineOfCreditAccount.frmOpenLineOfCreditAccount.setVisible(true);
				
				frmHomeNewAccount.dispose();
			}
		});
		btnLineOfCreditAccount.setBounds(117, 176, 192, 21);
		frmHomeNewAccount.getContentPane().add(btnLineOfCreditAccount);
		
		JLabel lblInstructions = new JLabel("Chose the type of accout you want to Add");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInstructions.setBounds(72, 11, 282, 14);
		frmHomeNewAccount.getContentPane().add(lblInstructions);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmHomeNewAccount.getContentPane().add(lblNewLabel);
	}

}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Account;
import bus.Customer;
import bus.Manager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class FormCloseAccount {

	JFrame frmCloseAccount;
	private JTextField textFieldPassword;
	private JTextField textFieldAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCloseAccount window = new FormCloseAccount((Integer)null);
					window.frmCloseAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormCloseAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmCloseAccount = new JFrame();
		frmCloseAccount.setTitle("Close Account");
		frmCloseAccount.setBounds(100, 100, 450, 249);
		frmCloseAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCloseAccount.getContentPane().setLayout(null);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPasswordMngr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordMngr.setBounds(10, 101, 152, 13);
		frmCloseAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 95, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldPassword);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer accountId = Integer.parseInt(textFieldAccount.getText());
					Manager.closeAccount(accountId);
					JOptionPane.showMessageDialog(null, "Account DELETED!!");
					
					FormMenuManager formMenuManager = new FormMenuManager(mgrId);
					formMenuManager.frmHomeManager.setVisible(true);
					
					frmCloseAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
				
			}
		});
		btnCloseAccount.setBounds(259, 178, 138, 21);
		frmCloseAccount.getContentPane().add(btnCloseAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				  JLabel lblHelloLabel = new JLabel("Chose your next operation");
				  lblHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
				  lblHelloLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  lblHelloLabel.setBounds(101, 26, 218, 14);
				  formMenuManager.frmHomeManager.getContentPane().add(lblHelloLabel);
				
				frmCloseAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 178, 77, 21);
		frmCloseAccount.getContentPane().add(btnCancel);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(172, 46, 225, 19);
		frmCloseAccount.getContentPane().add(textFieldAccount);
		
		JLabel lblAccountId = new JLabel("ID of the Account:");
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccountId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccountId.setBounds(23, 49, 139, 13);
		frmCloseAccount.getContentPane().add(lblAccountId);
		
		JLabel lblInformation = new JLabel("Delete Account");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(23, 11, 374, 19);
		frmCloseAccount.getContentPane().add(lblInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 105, 107, 105);
		frmCloseAccount.getContentPane().add(lblNewLabel);
		
		
	}
}

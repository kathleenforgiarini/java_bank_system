package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class FormMenuManager {

	JFrame frmHomeManager;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuManager window = new FormMenuManager((Integer)null);
					window.frmHomeManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuManager(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmHomeManager = new JFrame();
		frmHomeManager.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmHomeManager.setTitle("Home Manager");
		frmHomeManager.setBounds(100, 100, 450, 300);
		frmHomeManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomeManager.getContentPane().setLayout(null);
		
		JButton btnAddCustomer = new JButton("Add New Customer");
		btnAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAddNewCustomer formAddNewCustomer = new FormAddNewCustomer(mgrId);
				formAddNewCustomer.frmAddNewCustomer.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnAddCustomer.setBounds(137, 68, 151, 21);
		frmHomeManager.getContentPane().add(btnAddCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FormRemoveCustomer formRemoveCustomer = new FormRemoveCustomer(mgrId);
					formRemoveCustomer.frmRemoveCustomer.setVisible(true);
					
					frmHomeManager.dispose();
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
			}
		});
		btnRemoveCustomer.setBounds(137, 99, 151, 21);
		frmHomeManager.getContentPane().add(btnRemoveCustomer);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formOpenAccount = new FormMenuOpenAccount(mgrId);
				formOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnOpenAccount.setBounds(137, 130, 151, 21);
		frmHomeManager.getContentPane().add(btnOpenAccount);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCloseAccount formCloseAccount = new FormCloseAccount(mgrId);
				formCloseAccount.frmCloseAccount.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnCloseAccount.setBounds(137, 161, 151, 21);
		frmHomeManager.getContentPane().add(btnCloseAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.frmWelcomeToFortis.setVisible(true);
				
				frmHomeManager.dispose();
			}
		});
		btnExit.setBounds(309, 208, 85, 21);
		frmHomeManager.getContentPane().add(btnExit);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 145, 107, 105);
		frmHomeManager.getContentPane().add(lblNewLabel);
		
		
	}

}

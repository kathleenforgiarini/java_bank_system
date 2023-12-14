package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CreditAccount;
import bus.Customer;
import bus.EnumTypeAccount;
import bus.LineOfCreditAccount;
import bus.Manager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class FormOpenLineOfCreditAccount {

	JFrame frmOpenLineOfCreditAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldLimit;
	private JTextField textFieldInterestRate;
	private JLabel lblInformationLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenLineOfCreditAccount window = new FormOpenLineOfCreditAccount((Integer)null);
					window.frmOpenLineOfCreditAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenLineOfCreditAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenLineOfCreditAccount = new JFrame();
		frmOpenLineOfCreditAccount.setTitle("Open Line Of Credit Account");
		frmOpenLineOfCreditAccount.setBounds(100, 100, 450, 310);
		frmOpenLineOfCreditAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenLineOfCreditAccount.getContentPane().setLayout(null);
		
		JLabel lblUserid = new JLabel("Customer user id:");
		lblUserid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserid.setBounds(44, 41, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblUserid);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 37, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		/*
		 * JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		 * lblPasswordMngr.setBounds(23, 220, 113, 13);
		 * frmOpenLineOfCreditAccount.getContentPane().add(lblPasswordMngr);
		 * 
		 * textFieldPassword = new JTextField(); textFieldPassword.setColumns(10);
		 * textFieldPassword.setBounds(172, 214, 225, 19);
		 * frmOpenLineOfCreditAccount.getContentPane().add(textFieldPassword);
		 */
		
		JLabel lblDueDay = new JLabel("Due Date Day:");
		lblDueDay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDueDay.setBounds(44, 70, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDay);
		
		JSpinner spinnerDay = new JSpinner();
		spinnerDay.setBounds(211, 66, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerDay);
		
		JSpinner spinnerMonth = new JSpinner();
		spinnerMonth.setBounds(211, 98, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerMonth);
		
		JLabel lblDueDateMonth = new JLabel("Due Date Month:");
		lblDueDateMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDueDateMonth.setBounds(44, 102, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDateMonth);
		
		JLabel lblDueDateYear = new JLabel("Due Date Year:");
		lblDueDateYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDueDateYear.setBounds(44, 130, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblDueDateYear);
		
		JSpinner spinnerYear = new JSpinner();
		spinnerYear.setBounds(211, 126, 186, 20);
		frmOpenLineOfCreditAccount.getContentPane().add(spinnerYear);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					
					Integer day = (Integer) spinnerDay.getValue();
					Integer month = (Integer) spinnerMonth.getValue();
					Integer year = (Integer) spinnerYear.getValue();				
					LocalDate dueDate = LocalDate.of(year, month, day);
					
					Double limit = Double.parseDouble(textFieldLimit.getText());
					
					Double interestRate = Double.parseDouble(textFieldInterestRate.getText());
					
					Customer selectedCustomer = Customer.search(selectedId);
					
					Manager.openLineOfCreditAccount(selectedCustomer.getId(), dueDate, limit, interestRate);

					JOptionPane.showMessageDialog(null, "Line of Credit Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenLineOfCreditAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 243, 138, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenLineOfCreditAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 243, 77, 21);
		frmOpenLineOfCreditAccount.getContentPane().add(btnCancel);
		
		JLabel lblLimit = new JLabel("Limit of Credit Available:");
		lblLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimit.setBounds(44, 160, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblLimit);
		
		textFieldLimit = new JTextField();
		textFieldLimit.setColumns(10);
		textFieldLimit.setBounds(211, 156, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldLimit);
		
		textFieldInterestRate = new JTextField();
		textFieldInterestRate.setColumns(10);
		textFieldInterestRate.setBounds(211, 185, 186, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(textFieldInterestRate);
		
		JLabel lblInterestRate = new JLabel("Interest Rate:");
		lblInterestRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInterestRate.setBounds(44, 189, 139, 13);
		frmOpenLineOfCreditAccount.getContentPane().add(lblInterestRate);
		
		
		lblInformationLabel = new JLabel("New Line of Credit");
		lblInformationLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationLabel.setBounds(44, 11, 353, 19);
		frmOpenLineOfCreditAccount.getContentPane().add(lblInformationLabel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 170, 107, 105);
		frmOpenLineOfCreditAccount.getContentPane().add(lblNewLabel);
		
	}
}

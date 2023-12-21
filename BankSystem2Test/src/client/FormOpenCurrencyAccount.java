package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.CheckingAccount;
import bus.CurrencyAccount;
import bus.Customer;
import bus.EnumTypeAccount;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import bus.EnumTypeCurrency;
import bus.Manager;

public class FormOpenCurrencyAccount {

	JFrame frmOpenCurrencyAccount;
	private JTextField textFieldUserId;
	private JTextField textFieldPassword;
	private JTextField textFieldBalance;
	private JTextField textFieldCurrencyRate;
	private JTextField textFieldConversionFee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOpenCurrencyAccount window = new FormOpenCurrencyAccount((Integer)null);
					window.frmOpenCurrencyAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormOpenCurrencyAccount(Integer mgrId) {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer mgrId) {
		frmOpenCurrencyAccount = new JFrame();
		frmOpenCurrencyAccount.setTitle("Open Currency Account");
		frmOpenCurrencyAccount.setBounds(100, 100, 450, 300);
		frmOpenCurrencyAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenCurrencyAccount.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("Select customer user id:");
		lblUserId.setBounds(23, 28, 139, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(211, 22, 186, 19);
		frmOpenCurrencyAccount.getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblPasswordMngr = new JLabel("Confirm your password:");
		lblPasswordMngr.setBounds(23, 195, 113, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblPasswordMngr);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(172, 189, 225, 19);
		frmOpenCurrencyAccount.getContentPane().add(textFieldPassword);
		
		JComboBox<EnumTypeCurrency> comboBoxCurrency = new JComboBox<EnumTypeCurrency>();
		comboBoxCurrency.setModel(new DefaultComboBoxModel<EnumTypeCurrency>(EnumTypeCurrency.values()));
		comboBoxCurrency.setBounds(211, 80, 186, 21);
		frmOpenCurrencyAccount.getContentPane().add(comboBoxCurrency);
		
		JButton btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer selectedId = Integer.parseInt(textFieldUserId.getText());
					Double balance = Double.parseDouble(textFieldBalance.getText());
					Double currRate = Double.parseDouble(textFieldCurrencyRate.getText());
					Double currFee = Double.parseDouble(textFieldConversionFee.getText());
					EnumTypeCurrency combobox = (EnumTypeCurrency) comboBoxCurrency.getSelectedItem();
										
					Customer selectedCustomer = Customer.search(selectedId);
					
					Manager.openCurrencyAccount(selectedCustomer.getId(), balance, combobox, currRate, currFee);

					JOptionPane.showMessageDialog(null, "Currency Account Created!");
					
					FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
					formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
					
					frmOpenCurrencyAccount.dispose();
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnOpenAccount.setBounds(259, 218, 138, 21);
		frmOpenCurrencyAccount.getContentPane().add(btnOpenAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuOpenAccount formMenuOpenAccount = new FormMenuOpenAccount(mgrId);
				formMenuOpenAccount.frmHomeNewAccount.setVisible(true);
				
				frmOpenCurrencyAccount.dispose();
			}
		});
		btnCancel.setBounds(172, 218, 77, 21);
		frmOpenCurrencyAccount.getContentPane().add(btnCancel);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(211, 51, 186, 19);
		frmOpenCurrencyAccount.getContentPane().add(textFieldBalance);
		
		JLabel lblBalance = new JLabel("Insert the initial Balance:");
		lblBalance.setBounds(23, 57, 139, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblBalance);
		
		JLabel lblCurrency = new JLabel("Insert the currency:");
		lblCurrency.setBounds(23, 86, 178, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblCurrency);
		
		JLabel lblCurrencyRate = new JLabel("Insert the Currency Rate");
		lblCurrencyRate.setBounds(23, 115, 139, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblCurrencyRate);
		
		textFieldCurrencyRate = new JTextField();
		textFieldCurrencyRate.setColumns(10);
		textFieldCurrencyRate.setBounds(211, 109, 186, 19);
		frmOpenCurrencyAccount.getContentPane().add(textFieldCurrencyRate);
		
		textFieldConversionFee = new JTextField();
		textFieldConversionFee.setColumns(10);
		textFieldConversionFee.setBounds(211, 138, 186, 19);
		frmOpenCurrencyAccount.getContentPane().add(textFieldConversionFee);
		
		JLabel lblConversionFee = new JLabel("Insert the Conversion Fee");
		lblConversionFee.setBounds(23, 144, 139, 13);
		frmOpenCurrencyAccount.getContentPane().add(lblConversionFee);
	}
}

package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.Customer;
import bus.ExceptionIsNotANumber;
import bus.ExceptionIsNull;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FormRemoveCustomer {

	JFrame frmRemoveCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormRemoveCustomer window = new FormRemoveCustomer((Integer)null);
					window.frmRemoveCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ExceptionIsNull 
	 * @throws ExceptionIsNotANumber 
	 * @throws SQLException 
	 */
	public FormRemoveCustomer(Integer mgrId) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		initialize(mgrId);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ExceptionIsNull 
	 * @throws ExceptionIsNotANumber 
	 * @throws SQLException 
	 */
	private void initialize(Integer mgrId) throws SQLException, ExceptionIsNotANumber, ExceptionIsNull {
		
		ArrayList<Customer> customers = Customer.searchByManager(mgrId);
		
		frmRemoveCustomer = new JFrame();
		frmRemoveCustomer.setTitle("Remove Customer");
		frmRemoveCustomer.setBounds(100, 100, 450, 300);
		frmRemoveCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveCustomer.getContentPane().setLayout(null);
		
		Integer[] array = new Integer[customers.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] = customers.get(i).getId();
		}
		JComboBox comboBoxCustomer = new JComboBox(array);
		comboBoxCustomer.setBounds(146, 22, 120, 21);
		frmRemoveCustomer.getContentPane().add(comboBoxCustomer);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setBounds(23, 26, 113, 13);
		frmRemoveCustomer.getContentPane().add(lblCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer customerId = (Integer) comboBoxCustomer.getSelectedItem();

					Customer.remove(customerId);	
					JOptionPane.showMessageDialog(null, "Customer DELETED!!");
					
					FormMenuManager formMenuManager = new FormMenuManager(mgrId);
					formMenuManager.frmHomeManager.setVisible(true);
					
					frmRemoveCustomer.dispose();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
			}
		});
		btnRemoveCustomer.setBounds(259, 218, 138, 21);
		frmRemoveCustomer.getContentPane().add(btnRemoveCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuManager formMenuManager = new FormMenuManager(mgrId);
				formMenuManager.frmHomeManager.setVisible(true);
				
				frmRemoveCustomer.dispose();
			}
		});
		btnCancel.setBounds(172, 218, 77, 21);
		frmRemoveCustomer.getContentPane().add(btnCancel);

		
		
	}
}

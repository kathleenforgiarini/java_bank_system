package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenuTransfer {

	JFrame frmMenuTransfer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuTransfer window = new FormMenuTransfer((Integer)null);
					window.frmMenuTransfer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMenuTransfer(Integer customerId) {
		initialize(customerId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer customerId) {
		frmMenuTransfer = new JFrame();
		frmMenuTransfer.setTitle("Menu Transfer");
		frmMenuTransfer.setBounds(100, 100, 450, 300);
		frmMenuTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuTransfer.getContentPane().setLayout(null);
		
		JButton btnCheckingToChecking = new JButton("Transfer between Checking Accounts");
		btnCheckingToChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferBetweenChecking formTransferBetweenChecking = new FormTransferBetweenChecking(customerId);
				formTransferBetweenChecking.frmTransferBetweenChecking.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnCheckingToChecking.setBounds(100, 47, 238, 21);
		frmMenuTransfer.getContentPane().add(btnCheckingToChecking);
		
		JButton btnCheckingToSaving = new JButton("Transfer From Checking to Saving Accounts");
		btnCheckingToSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferFromCheckingToSaving formTransferFromCheckingToSaving = new FormTransferFromCheckingToSaving(customerId);
				formTransferFromCheckingToSaving.frmTransferFromCheckingToSaving.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnCheckingToSaving.setBounds(100, 78, 238, 21);
		frmMenuTransfer.getContentPane().add(btnCheckingToSaving);
		
		JButton btnSavingToChecking = new JButton("Transfer From Saving to Checking Accounts");
		btnSavingToChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferFromSavingToChecking formTransferFromSavingToChecking = new FormTransferFromSavingToChecking(customerId);
				formTransferFromSavingToChecking.frmTransferFromSavingToChecking.setVisible(true);
				frmMenuTransfer.dispose();
				
			}
		});
		btnSavingToChecking.setBounds(100, 109, 238, 21);
		frmMenuTransfer.getContentPane().add(btnSavingToChecking);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuCustomer formMenuCustomer = new FormMenuCustomer(customerId);
				formMenuCustomer.frmHomeCustomer.setVisible(true);
				
				frmMenuTransfer.dispose();
			}
		});
		btnExit.setBounds(309, 217, 85, 21);
		frmMenuTransfer.getContentPane().add(btnExit);
		
		JButton btnSavingToSaving = new JButton("Transfer between Saving Accounts");
		btnSavingToSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferBetweenSaving formTransferBetweenSaving = new FormTransferBetweenSaving(customerId);
				formTransferBetweenSaving.frmTransferBetweenSaving.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnSavingToSaving.setBounds(100, 145, 238, 21);
		frmMenuTransfer.getContentPane().add(btnSavingToSaving);
	}
}

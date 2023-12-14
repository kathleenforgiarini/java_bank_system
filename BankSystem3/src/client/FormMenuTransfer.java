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
		
		JButton btnCheckingToChecking = new JButton("Between Checking Accounts");
		btnCheckingToChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferBetweenChecking formTransferBetweenChecking = new FormTransferBetweenChecking(customerId);
				formTransferBetweenChecking.frmTransferBetweenChecking.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnCheckingToChecking.setBounds(100, 66, 238, 21);
		frmMenuTransfer.getContentPane().add(btnCheckingToChecking);
		
		JButton btnCheckingToSaving = new JButton("From Checking to Saving Accounts");
		btnCheckingToSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferFromCheckingToSaving formTransferFromCheckingToSaving = new FormTransferFromCheckingToSaving(customerId);
				formTransferFromCheckingToSaving.frmTransferFromCheckingToSaving.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnCheckingToSaving.setBounds(100, 97, 238, 21);
		frmMenuTransfer.getContentPane().add(btnCheckingToSaving);
		
		JButton btnSavingToChecking = new JButton("From Saving to Checking Accounts");
		btnSavingToChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferFromSavingToChecking formTransferFromSavingToChecking = new FormTransferFromSavingToChecking(customerId);
				formTransferFromSavingToChecking.frmTransferFromSavingToChecking.setVisible(true);
				frmMenuTransfer.dispose();
				
			}
		});
		btnSavingToChecking.setBounds(100, 128, 238, 21);
		frmMenuTransfer.getContentPane().add(btnSavingToChecking);
		
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
				
				frmMenuTransfer.dispose();
			}
		});
		btnExit.setBounds(309, 217, 85, 21);
		frmMenuTransfer.getContentPane().add(btnExit);
		
		JButton btnSavingToSaving = new JButton("Between Saving Accounts");
		btnSavingToSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTransferBetweenSaving formTransferBetweenSaving = new FormTransferBetweenSaving(customerId);
				formTransferBetweenSaving.frmTransferBetweenSaving.setVisible(true);
				frmMenuTransfer.dispose();
			}
		});
		btnSavingToSaving.setBounds(100, 164, 238, 21);
		frmMenuTransfer.getContentPane().add(btnSavingToSaving);
		
		JLabel lblInformation = new JLabel("Choose the option of transfer you want to do");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(10, 22, 414, 21);
		frmMenuTransfer.getContentPane().add(lblInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/FortisBank.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 156, 107, 105);
		frmMenuTransfer.getContentPane().add(lblNewLabel);
		
	}
}

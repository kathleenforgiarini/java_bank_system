package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import bus.Transaction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FormShowTransactions {

	JFrame frmShowTransactions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormShowTransactions window = new FormShowTransactions((ArrayList<Transaction>)null);
					window.frmShowTransactions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormShowTransactions(ArrayList<Transaction> transactions) {
		initialize(transactions);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Transaction> transactions) {
		frmShowTransactions = new JFrame();
		frmShowTransactions.setTitle("Your account statement");
		frmShowTransactions.setBounds(100, 100, 450, 300);
		frmShowTransactions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowTransactions.getContentPane().setLayout(null);
		
		JTextArea textAreaTransactions = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textAreaTransactions);
		scrollPane.setBounds(10, 10, 416, 209);
		frmShowTransactions.getContentPane().add(scrollPane);

		
		String stringToShow = "";
		for (Transaction transaction : transactions) {
			stringToShow += transaction.toString() + "\n";
		}
		
		textAreaTransactions.setText(stringToShow);
		textAreaTransactions.setCaretPosition(0);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmShowTransactions.dispose();
			}
		});
		btnExit.setBounds(341, 232, 85, 21);
		frmShowTransactions.getContentPane().add(btnExit);
		

	}
}

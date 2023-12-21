package bus;

import java.util.ArrayList;
import java.util.Collections;

import data.TransactionDB;

import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionCollection{
	
	public static ArrayList<Transaction> searchByMonth(LocalDate date, ArrayList<Transaction> listOfTransactions) throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull{

		ArrayList<Transaction> listOfTransactionsByMonth = new ArrayList<Transaction>();
		
		for (Transaction element : listOfTransactions)
		{			
			if (element.getTransactionDate().getMonth() == date.getMonth() && 
					element.getTransactionDate().getYear() == date.getYear())
			{
				listOfTransactionsByMonth.add(element);
			}
		}
		return listOfTransactionsByMonth;
	}
	
	public static void sortByDate(ArrayList<Transaction> transactions, PredicateDate datePredicate) throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull
	{
		Collections.sort(transactions,datePredicate);
	}
	
	public static void sortByAmount(ArrayList<Transaction> transactions, PredicateAmount amountPredicate) throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull
	{
		Collections.sort(transactions,amountPredicate);
	}
	
	public static Integer getCountThisMonth(LocalDate date, ArrayList<Transaction> transactions) throws SQLException, ExceptionNegativeAmount, ExceptionIsNotANumber, ExceptionIsNull {
		ArrayList<Transaction> countTransactions = searchByMonth(date, transactions);
		return countTransactions.size();
	}
}

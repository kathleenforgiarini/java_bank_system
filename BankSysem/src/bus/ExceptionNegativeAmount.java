package bus;

public class ExceptionNegativeAmount extends Exception {
	
	private final static String message = "Negative Amount Exception - Amount must be a positive number";

	public ExceptionNegativeAmount()
	{
		super(message);
	}
	
	public ExceptionNegativeAmount(String newMessage)
	{
		super(newMessage);
	}
}

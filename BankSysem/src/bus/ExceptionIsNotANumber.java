package bus;

public class ExceptionIsNotANumber extends Exception {
	
	private final static String message = "The value provided must be a number";

	public ExceptionIsNotANumber()
	{
		super(message);
	}
	
	public ExceptionIsNotANumber(String newMessage)
	{
		super(newMessage);
	}
}

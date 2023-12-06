package bus;

public class ExceptionIsNull extends Exception {
	
	private final static String message = "The value can not be null";

	public ExceptionIsNull()
	{
		super(message);
	}
	
	public ExceptionIsNull(String newMessage)
	{
		super(newMessage);
	}
}

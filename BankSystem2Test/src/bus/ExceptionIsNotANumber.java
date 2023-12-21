package bus;

public class ExceptionIsNotANumber extends Exception {

	private static final long serialVersionUID = -2507853083138234136L;
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

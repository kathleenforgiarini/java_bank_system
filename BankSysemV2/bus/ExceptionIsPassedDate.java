package bus;

public class ExceptionIsPassedDate extends Exception {
	
	private static final long serialVersionUID = -1645724463865267203L;
	private final static String message = "The due date can not be in the past";

	public ExceptionIsPassedDate()
	{
		super(message);
	}
	
	public ExceptionIsPassedDate(String newMessage)
	{
		super(newMessage);
	}
}

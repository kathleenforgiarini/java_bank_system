package bus;

public class ExceptionNotEnoughLimit extends Exception {

	private static final long serialVersionUID = 9148003460073601997L;
	private final static String message = "You don't have enough limit!";

	public ExceptionNotEnoughLimit()
	{
		super(message);
	}
	
	public ExceptionNotEnoughLimit(String newMessage)
	{
		super(newMessage);
	}
}

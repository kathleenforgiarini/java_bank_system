package bus;

public class ExceptionNotEnoughBalance extends Exception {

	private static final long serialVersionUID = 9148003460073601997L;
	private final static String message = "Not enough funds to complete the operation";

	public ExceptionNotEnoughBalance()
	{
		super(message);
	}
	
	public ExceptionNotEnoughBalance(String newMessage)
	{
		super(newMessage);
	}
}

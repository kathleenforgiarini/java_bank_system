package bus;

public class ExceptionIsNull extends Exception {
	
	private static final long serialVersionUID = -1247972375783820661L;
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

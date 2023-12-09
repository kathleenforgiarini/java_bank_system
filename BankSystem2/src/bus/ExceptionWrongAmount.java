package bus;

public class ExceptionWrongAmount extends Exception {
	
	private static final long serialVersionUID = 98178722091082961L;
	private final static String message = "The amount must be at least your installment value";

	public ExceptionWrongAmount()
	{
		super(message);
	}
	
	public ExceptionWrongAmount(String newMessage)
	{
		super(newMessage);
	}
}

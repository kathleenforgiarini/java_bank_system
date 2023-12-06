package bus;

public class ExceptionLatePayment extends Exception {
	
	private static final long serialVersionUID = 9028665147608205540L;
	private final static String message = "The payment must be done before due date.";

	public ExceptionLatePayment()
	{
		super(message);
	}
	
	public ExceptionLatePayment(String newMessage)
	{
		super(newMessage);
	}
}

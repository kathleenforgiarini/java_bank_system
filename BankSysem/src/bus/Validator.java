package bus;

public class Validator {
	public static boolean isNegativeOrZero (Double number) throws ExceptionNegativeAmount
	{
		boolean valid = false;
		
		if (number <= 0)
		{
			valid = true;
		}
		return valid;
	}
}

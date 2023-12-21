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
	
	public static boolean isInteger (Integer number) throws ExceptionIsNotANumber {
		boolean valid = false;
		
		if (number == (int)number) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean isDouble (Double number) throws ExceptionIsNotANumber {
		boolean valid = false;
		
		if (!Double.isNaN(number)) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean isNull (Integer number) throws ExceptionIsNull {
		boolean valid = false;
		
		if (number == null) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean isNull (Double number) throws ExceptionIsNull {
		boolean valid = false;
		
		if (number == null) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean isNull (String str) throws ExceptionIsNull {
		boolean valid = false;
		
		if (str == null) {
			valid = true;
		}
		return valid;
	}
}

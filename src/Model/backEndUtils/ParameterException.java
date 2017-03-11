package Model.backEndUtils;

public class ParameterException extends RuntimeException {

	public static final String INVALID_PAR = "%s is not a valid variable";
	
	private static final long serialVersionUID = 1L;
	
	public ParameterException(String message) {
        super(message);
    }
	
    public ParameterException(String message, Object ... values) {
        super(String.format(message, values));
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }
}
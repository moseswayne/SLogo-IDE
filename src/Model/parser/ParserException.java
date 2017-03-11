package Model.parser;

public class ParserException extends RuntimeException {

	public static final String INVALID_CMD = "%s is not a recognized command";
	public static final String INVALID_SYN = "Syntax for the entered command is incorrect";
	public static final String INVALID_VAR = "%s is not a recognized variable";
	public static final String PROPERTIES_ERROR = "Error reading properties file";
	
	private static final long serialVersionUID = 1L;
	
	public ParserException(String message) {
        super(message);
    }
	
    public ParserException(String message, Object ... values) {
        super(String.format(message, values));
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
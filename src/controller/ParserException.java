package controller;

public class ParserException extends RuntimeException {

	public static final String INVALID_CMD = "The command is not recognized";
	
	private static final long serialVersionUID = 1L;
	
	public ParserException(String message) {
        super(message);
    }
	
    public ParserException(Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
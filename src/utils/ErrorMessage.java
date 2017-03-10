package utils;

/**
 * Class that contains information about errors to be shown on front end
 * @author Yuxiang He
 *
 */
public class ErrorMessage {
	private String errorType;
	private String extraInfo;
	
	public ErrorMessage(String _errorType) {
		this(_errorType, null);
	}
	
	public ErrorMessage(String _errorType, String _extraInfo) {
		errorType=_errorType;
		extraInfo=_extraInfo;
	}
	
	public String getMessage(){
		if(extraInfo!=null){
			return String.format("%s: %s", errorType, extraInfo);
		} else {
			return String.format("%s", errorType);
		}
		
	}
	

}

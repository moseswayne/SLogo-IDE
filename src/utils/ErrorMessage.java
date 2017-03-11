package utils;

import java.util.Properties;

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
	
	public ErrorMessage(Exception e){
		this(e.getMessage(), e.toString());
	}
	
	public ErrorMessage(String _errorType, String _extraInfo) {
		errorType=_errorType;
		extraInfo=_extraInfo;
	}
	
	public String getMessage(){
		Properties prop=new PropertyUtility("GeneraGUISettings.properties").getProperties();
		if(extraInfo!=null){
			return String.format(prop.getProperty("extraInfoErrorMsgTemplate"), errorType, extraInfo);
		} else {
			return String.format(prop.getProperty("deafaultErrorMsgTemplate"), errorType);
		}
	}
	

}

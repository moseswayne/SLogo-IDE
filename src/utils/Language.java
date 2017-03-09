package utils;

/**
 * specifies all the supported languages in SLogo
 * @author Yuxiang He
 *
 */
public enum Language {
	
	Chinese("Chinese"),
	English("English"),
	French("French"),
	German("German"),
	Italian("Italian"),
	Portuguese("Portuguese"),
	Russian("Russian"),
	Spanish("Spanish");
	
	//make sure the string matches the name of the corresponding property file
	private final String stringName;
	
	private Language(String _stringName){
		this.stringName=_stringName;
	}
	
	/**
	 * return a string version of this name
	 * ONLY USE THIS FOR getting a resource file
	 * DO NOT use for other purposes
	 */
	public String toString(){
		return stringName;
	}
}

package utils;


public class RawCommand {
	private String rawStr;
	private Language language;

	
	public RawCommand(RawCommand other){
		this.rawStr=other.getCommandString();
		this.language=other.getLanguage();
	}
	
	public RawCommand(String _rawStr, Language _language) {
		rawStr=_rawStr;
		language=_language;
	}

	
	
	public String getCommandString() {
		return rawStr;
	}
	
	public Language getLanguage(){
		return language;
	}
	
}

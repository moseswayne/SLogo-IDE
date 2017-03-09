package utils;

import java.util.Map;

public class RawCommand {
	private String rawStr;
	private String language;
	private Map<String, String> translationMap;
	
	public RawCommand(RawCommand other){
		this.rawStr=other.getCommandString();
		this.language=other.getLanguage();
		this.translationMap=other.getMap();
	}
	
	public RawCommand(String _rawStr, Map<String, String> _translationMap, String _language) {
		rawStr=_rawStr;
		translationMap=_translationMap;
		language=_language;
	}
	
	public RawCommand(String _rawStr,  String _language) {
		this(_rawStr, null, _language);
	}
	
	public void setTranslationMap(Map<String, String> _translationMap){
		translationMap=_translationMap;
	}
	
	public String getCommandString() {
		return rawStr;
	}
	
	public Map<String, String> getMap() {
		return translationMap;
	}
	
	public String getLanguage(){
		return language;
	}
	
}

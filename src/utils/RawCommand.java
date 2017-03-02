package utils;

import java.util.Map;

public class RawCommand {
	private String rawStr;
	private Map<String, String> translationMap;
	
	public RawCommand(String _rawStr, Map<String, String> _translationMap) {
		rawStr=_rawStr;
		translationMap=_translationMap;
	}
	
	public RawCommand(String _rawStr) {
		this(_rawStr, null);
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
	
}

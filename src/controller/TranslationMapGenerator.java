package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TranslationMapGenerator {
	private Properties languageProp;
	private final String[] LANGUAGES={"Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};
	private Map<String, Map<String, String>> translationMaps;
	
	
	public TranslationMapGenerator() {
		languageProp=new Properties();
		translationMaps=new HashMap<>();
		buildMaps();
	}

	/**
	 * gets the translationMap for the language
	 * @param language
	 * @return
	 */
	public Map<String, String> getTranslationMap(String language){
		if(translationMaps.get(language)!=null){
			return translationMaps.get(language);
		} else {
			throw new IllegalArgumentException("unknown language");
		}
	}
	
	/**
	 * build the translation maps of the different strings
	 */
	private void buildMaps() {
		for(String language: LANGUAGES){
			translationMaps.put(language, buildTranslationMap(language));
		}
	}

	/**
	 * Builds the translation map of the language
	 * loop through the property file to get reverse of the mapping, 
	 * helps back end implementation
	 * @return
	 */
	private Map<String, String> buildTranslationMap(String language){
		try {
			languageProp.load(getClass().getClassLoader().getResourceAsStream(language+".properties"));
		} catch (IOException e1) {
			throw new Error("Language property file not found or something else created an IO error");
		}
		HashMap<String, String> out=new HashMap<>();
		for(Object command: languageProp.keySet()){
			String cmdString=(String) command;
			String match=languageProp.getProperty(cmdString);
			String[] matches=match.split("\\|");
			for(String m: matches){
				out.put(m, cmdString);
			}
		}
		return out;
	}
}

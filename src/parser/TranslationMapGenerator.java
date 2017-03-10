package parser;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import utils.Language;

public class TranslationMapGenerator {
	private Properties languageProp;
	private Map<Language, Map<String, String>> translationMaps;
	
	
	public TranslationMapGenerator() {
		languageProp=new Properties();
		translationMaps=new EnumMap<Language, Map<String, String>>(Language.class);
		buildMaps();
	}

	/**
	 * gets the translationMap for the language
	 * @param language
	 * @return
	 */
	public Map<String, String> getTranslationMap(Language language){
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
		for(Language language: Language.values()){
			translationMaps.put(language, buildTranslationMap(language));
		}
	}

	/**
	 * Builds the translation map of the language
	 * loop through the property file to get reverse of the mapping, 
	 * helps back end implementation
	 * @return
	 */
	private Map<String, String> buildTranslationMap(Language language){
		try {
			languageProp.load(getClass().getClassLoader().getResourceAsStream(language.toString()+".properties"));
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

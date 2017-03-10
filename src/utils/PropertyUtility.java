package utils;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class PropertyUtility {
	
	Properties myProperties;
	
	public PropertyUtility(String fileName){
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		myProperties = prop;
	}
	
	public Properties getProperties() {
		return myProperties;
	}
	
	public String getKey(String value) {
		String retKey = null;
		for (Object key:myProperties.keySet()) {
			if(Pattern.matches((String) myProperties.get(key), value)) {
				retKey = (String) key;
			}
		}
		return retKey;
	}
	
	public String getValue(String key) {
		return myProperties.getProperty(key);
	}
}

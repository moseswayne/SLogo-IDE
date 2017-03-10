package utils;

import java.io.IOException;
import java.util.Properties;

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
}

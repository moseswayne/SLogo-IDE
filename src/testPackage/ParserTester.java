package testPackage;

import java.util.Properties;
import java.util.regex.Pattern;

import controller.CommandParser;
import utils.PropertyUtility;

public class ParserTester {

	public static void main(String args[]) {
		CommandParser p = new CommandParser();
		PropertyUtility pb = new PropertyUtility();
		Properties prop = pb.loadPropetiesFromFile("resources/languages/English.properties");
		for (Object key:prop.keySet()) {
			System.out.println(key);
			System.out.println(prop.getProperty((String) key));
			System.out.println(Pattern.matches(prop.getProperty((String) key), "sum"));
		}
		//p.parse(rawString, translationMap)
	}
	
}

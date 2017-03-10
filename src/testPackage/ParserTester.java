package testPackage;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import controller.CommandParser;
import utils.Language;
import utils.PropertyUtility;

public class ParserTester {

	public static void main(String args[]) {
		ParserTester pt = new ParserTester();
		CommandParser p = new CommandParser();
		for (Object key:pt.getProp().keySet()) {
			System.out.println(key);
			System.out.println(pt.getProp().getProperty((String) key));
			System.out.println(Pattern.matches(pt.getProp().getProperty((String) key), "sum"));
		}
		p.parse("sum 4 3 for #[ :x 3 2 0 ]", Language.English);
	}
	
	public Properties getProp(){
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("resources/languages/English.properties"));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		return prop;
	}
	
}

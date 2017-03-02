package controller;

import utils.ParameterObject;

import java.util.Map;
import java.util.Properties;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.File;


public class Parser {
	
	private Properties properties;
	private String cmd;
	private Map<String, String> cmdMap;
	private Map<String, Double> varMap;
	private ParameterObject params;
	
	public Parser(String command, Map<String, String> commandMap, Map<String, Double> variableMap) {
		cmd = command;
		cmdMap = commandMap;
		varMap = variableMap;
		
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File("resources.languages/Syntax.properties")));
		} catch {}
		
		parseCommand();
	}
	
	public ParameterObject getParameters() {
		return params;
	}
	
	private void parseCommand() {
		String rootCmd = getRootCommand(cmd);
		rootCmd = Constant;
	}
	
	private String getRootCommand(String cmd) {
		String rootCmd = cmd.split(" ")[0];
		if (!isValidCommand(rootCmd)) throw new ParserException(ParserException.INVALID_CMD);
		return rootCmd;
	}
	
	private boolean isValidCommand(String cmd) {
		return cmdMap.containsKey(cmd) && cmdMap.get(cmd).length() != 0;
	}
}
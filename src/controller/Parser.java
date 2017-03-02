package controller;

import utils.ParameterObject;
import java.util.Map;
import java.util.ArrayList;

public class Parser {
	
	private String cmd;
	private Map<String, String> cmdMap;
	private Map<String, Double> varMap;
	private ParameterObject params;
	
	public Parser(String command, Map<String, String> commandMap, Map<String, Double> variableMap) {
		cmd = command;
		cmdMap = commandMap;
		varMap = variableMap;
		parseCommand();
	}
	
	public ParameterObject getParameters {
		
	}
	
	private void parseCommand() {
		String rootCmd = getRootCommand(cmd);
		
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
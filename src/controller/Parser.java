package controller;

import utils.ParameterObject;

import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ResourceBundle;

import tree.CommandNode;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.File;


public class Parser {

	private ResourceBundle myResourceBundle;
	private String command;
	private Map<String, String> commandsMap;
	private Map<String, Double> variablesMap;
	private ParameterObject params;
	
	public Parser(Map<String, Double> varMap) {
		variablesMap = varMap;
		myResourceBundle = ResourceBundle.getBundle("resources.languages/Syntax.properties");
	}
	
	public ParameterObject getParameters() {
		return params;
	}
	
	private ArrayList<CommandNode> parseCommand(String cmd) {
		command = cmd;
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
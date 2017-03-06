package controller;

import utils.ParameterObject;

import java.util.Map;
import java.util.ResourceBundle;

import tree.CommandNode;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map.Entry;
import java.util.Enumeration;
import java.util.AbstractMap.SimpleEntry;

public class Parser {

	private String command;
	private Map<String, String> commandsMap;
	private Map<String, Double> variablesMap;
	private ParameterObject params;
	private List<Entry<String, Pattern>> mySymbols;
	
	public Parser(Map<String, Double> varMap) {
		variablesMap = varMap;
		mySymbols = new ArrayList<>();
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
	
	public String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }
	
	private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
	
	private boolean isValidCommand(String cmd) {
		return commandsMap.containsKey(cmd) && commandsMap.get(cmd).length() != 0;
	}
}
package controller;

import tree.CommandNode;
import Operations.CommandOperation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Parser {

	private final String PROPERTIES_FILE = "resources/Arguments.properties";
	
	private Properties properties;
	private Map<String, String> commandMap;
	private Map<String, Double> variableMap;
	private OperationFactory operationFactory;
	
	public Parser(Map<String, Double> varMap, Map<String, String> cmdMap) {
		variableMap = varMap;
		commandMap = cmdMap;
		operationFactory = new OperationFactory();
		properties = getPropertiesFile();
	}
	
	private Collection<CommandNode> parseCommand(String expression) {
		String rootCmd = getRootCommand(expression);
		CommandNode rootNode = new CommandNode(getExpressionTree(rootCmd, expression));
		rootNode.setMyValue(rootCmd);
	}
	
	private Collection<CommandNode> getExpressionTree(String rootCmd, String cmdBody) {
		ArrayList<CommandNode> rootNodes = new ArrayList<CommandNode>();
		
	}
	
	private CommandOperation getOp(String cmdName) {
		return operationFactory.getOp(cmdName);
	}
	
	private String getRootCommand(String expression) {
		String rootCmd = expression.split(" ")[0];
		if (!isValidCommand(rootCmd)) {
			throw new ParserException(ParserException.INVALID_CMD, rootCmd);
		}
		return rootCmd;
	}
	
	private int numArgs(String cmdName) {
		return Integer.parseInt(properties.getProperty(cmdName));
	}
	
	private Properties getPropertiesFile() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(PROPERTIES_FILE)));
		} catch (Exception e) {
			throw new ParserException(ParserException.PROPERTIES_ERROR);
		}
		return prop;
	}
	
	private boolean isValidCommand(String cmd) {
		return commandMap.containsKey(cmd) && commandMap.get(cmd).length() != 0;
	}
	
	private boolean isValidVariable(String var) {
		return variableMap.containsKey(var) && variableMap.get(var) != null;
	}
}
package controller;

import tree.CommandNode;
import Operations.CommandOperation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Collection;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class Parser {

	private final String ARGS_PROPERTIES_FILE = "resources/Arguments.properties";
	private final String SYNTAX_PROPERTIES_FILE = "resources.languages/Syntax.properties";
	
	private final String COMMENT = regex("Comment");
	private final String CONSTANT = regex("Constant");
	private final String VARIABLE = regex("Variable");
	private final String COMMAND = regex("Command");
	private final String LISTSTART = regex("ListStart");
	private final String LISTEND = regex("ListEnd");
	private final String GROUPSTART = regex("GroupStart");
	private final String GROUPEND = regex("GroupEnd");
	private final String WHITESPACE = regex("Whitespace");
	private final String NEWLINE = regex("NewLine");
	
	private Properties argProperties;
	private Properties syntaxProperties;
	private Map<String, String> commandMap;
	private Map<String, Double> variableMap;
	private OperationFactory operationFactory;
	
	public Parser(Map<String, Double> varMap, Map<String, String> cmdMap) {
		variableMap = varMap;
		commandMap = cmdMap;
		operationFactory = new OperationFactory(commandMap);
		argProperties = getPropertiesFile(ARGS_PROPERTIES_FILE);
		syntaxProperties = getPropertiesFile(SYNTAX_PROPERTIES_FILE);
	}
	
	public Collection<CommandNode> parseCommand(String expression) {
		ArrayList<CommandNode> cmdTrees = new ArrayList<CommandNode>(0);
		expression = removeCommentsAndFlatten(expression);
		Tokenizer tokens = new Tokenizer(expression, WHITESPACE);
		while (tokens.hasNextToken()) {
			String rootCmd = tokens.nextToken();
			if (!operationFactory.isValidOp(rootCmd)) {
				throw new ParserException(ParserException.INVALID_CMD, rootCmd);
			}
			cmdTrees.add(buildTreeForCommand(rootCmd, tokens));
		}
		return cmdTrees;
	}
	
	private CommandNode buildTreeForCommand(String cmdName, Tokenizer tokens) {
		int numArgs = numArgs(cmdName);
		CommandNode rootNode = new CommandNode();
		rootNode.setMyValue(cmdName);
		rootNode.setOp(getOp(cmdName));
		while (numArgs > 0) {
			String currentToken = tokens.nextToken();
			if (Pattern.matches(CONSTANT, currentToken)) {
				rootNode.addChild(makeLeafNode(currentToken));
				numArgs--;
			}
			else if (Pattern.matches(VARIABLE, currentToken)) {
				if (!isValidVar(currentToken)) {
					throw new ParserException(ParserException.INVALID_VAR, currentToken);
				}
				rootNode.addChild(makeLeafNode(currentToken));
				numArgs--;
			}
			else if (Pattern.matches(COMMAND, currentToken)) {
				if (!operationFactory.isValidOp(currentToken)) {
					throw new ParserException(ParserException.INVALID_CMD, currentToken);
				}
				rootNode.addChild(buildTreeForCommand(currentToken, tokens));
				numArgs--;
			}
			else if (Pattern.matches(LISTSTART, currentToken)) {
				
			}
		}
		return rootNode;
	}
	
	private CommandNode makeLeafNode(String value) {
		CommandNode leafNode = new CommandNode();
		leafNode.setMyValue(value);
		return leafNode;
	}
	
	private CommandOperation getOp(String cmdName) {
		return operationFactory.getOp(cmdName);
	}
	
	private String removeCommentsAndFlatten(String expression) {
		String[] lines = expression.split(NEWLINE);
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			if (!Pattern.matches(COMMENT, line)) {
				sb.append(line);
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	private int numArgs(String cmdName) {
		if (operationFactory.isValidOp(cmdName)) {
			return Integer.parseInt(argProperties.getProperty(cmdName));
		}
		else return 0;
	}
	
	private String regex(String syntax) {
		return syntaxProperties.getProperty(syntax);
	}
	
	private boolean isValidVar(String varName) {
		return variableMap.containsKey(varName) && variableMap.get(varName) != null;
	}
	
	private Properties getPropertiesFile(String filePath) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(filePath)));
		} catch (Exception e) {
			throw new ParserException(ParserException.PROPERTIES_ERROR);
		}
		return prop;
	}
}
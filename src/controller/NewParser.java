package controller;

import tree.CommandNode;
import Operations.CommandOperation;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Collection;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class NewParser {

	private final String ARGS_PROPERTIES_FILE = "Arguments.properties";
	private final String SYNTAX_PROPERTIES_FILE = "Syntax.properties";
	
	private Properties argProperties;
	private Properties syntaxProperties;
	private Map<String, String> commandMap;
	private OperationFactory operationFactory;
	
	private final String COMMENT = getRegex("Comment");
	private final String CONSTANT = getRegex("Constant");
	private final String VARIABLE = getRegex("Variable");
	private final String COMMAND = getRegex("Command");
	private final String LISTSTART = getRegex("ListStart");
	private final String LISTEND = getRegex("ListEnd");
	private final String GROUPSTART = getRegex("GroupStart");
	private final String GROUPEND = getRegex("GroupEnd");
	private final String WHITESPACE = getRegex("Whitespace");
	private final String NEWLINE = getRegex("NewLine");
	
	
	public NewParser(Map<String, String> cmdMap, Map<String, String> translationMap) {
		commandMap = cmdMap;
		operationFactory = new OperationFactory(commandMap);
		argProperties = getPropertiesFile(ARGS_PROPERTIES_FILE);
		syntaxProperties = getPropertiesFile(SYNTAX_PROPERTIES_FILE);
	}
	
	
	
	public Collection<CommandNode> parseCommand(String expression) {
		ArrayList<CommandNode> cmdTrees = new ArrayList<CommandNode>();
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
				// TODO: handle brackets
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
	
	private String getRegex(String syntax) {
		return syntaxProperties.getProperty(syntax);
	}
	
	
	private Properties getPropertiesFile(String fileName){
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
			return prop;
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
	}
}

//                           _
//                        _ooOoo_
//                       o8888888o
//                       88" . "88
//                       (| -_- |)
//                       O\  =  /O
//                    ____/`---'\____
//                  .'  \\|     |//  `.
//                 /  \\|||  :  |||//  \
//                /  _||||| -:- |||||_  \
//                |   | \\\  -  /'| |   |
//                | \_|  `\`---'//  |_/ |
//                \  .-\__ `-. -'__/-.  /
//              ___`. .'  /--.--\  `. .'___
//           ."" '<  `.___\_<|>_/___.' _> \"".
//          | | :  `- \`. ;\_ _/; .'/ /  .' ; |
//          \  \ `-.   \_\_`. _.'_/_/  -' _.' /
//===========`-.`___`-.__\ \___/ /__.-'___'.-'===========
//                        `=---='
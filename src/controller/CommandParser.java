package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.regex.Pattern;

import tree.ExpressionNode;
import utils.PropertyUtility;

public class CommandParser {
	private final String syntaxFile = "resources/Syntax.properties";

	private PropertyUtility propertyParser;
	private Properties syntaxProp;

	public CommandParser() {
		propertyParser = new PropertyUtility();
	}

	public Collection<ExpressionNode> parse(String rawString, Map<String, String> translationMap) {
		syntaxProp = propertyParser.loadPropetiesFromFile(syntaxFile);
		String[] commandLines = rawString.split(syntaxProp.getProperty("Newline"));
		Queue<String> commandTokens = buildQueue(commandLines, translationMap);
		while (!commandTokens.isEmpty()) {
			System.out.println(commandTokens.poll());
		}
		ArrayList<ExpressionNode> myNodes = new ArrayList<>();
		return myNodes;
	}

	private Queue<String> buildQueue(String[] commands, Map<String, String> translationMap) {
		Queue<String> commandQueue = new ArrayDeque<>();
		for (String line : commands) {
			String[] commandTokens = line.split(syntaxProp.getProperty("Whitespace"));
			for (String token : commandTokens) {
				if (Pattern.matches(syntaxProp.getProperty("Comment"), token)) break;
				String addString = token;
				if (Pattern.matches(syntaxProp.getProperty("Command"), token)) {
					if (translationMap.containsKey(token)) {
						addString = translationMap.get(token);
					} else {
						throw new ParserException(ParserException.INVALID_CMD,token);
					}
				}
				commandQueue.add(addString);
			}
		}
		return commandQueue;
	}

}
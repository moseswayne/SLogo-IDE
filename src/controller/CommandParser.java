package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.regex.Pattern;

import Model.TurtleManager;
import tree.ExpressionNode;
import utils.Language;
import utils.PropertyUtility;

public class CommandParser {
	private final String syntaxFile = "resources/Syntax.properties";
	private final String languageDir = "resources/languages/";
	private final String fileExtension = ".properties";

	private PropertyUtility mySyntaxProp;
	private PropertyUtility myLanguage;

	public CommandParser() {
		mySyntaxProp = new PropertyUtility(syntaxFile);
	}

	public Collection<ExpressionNode> parse(String rawString, Language language, TurtleManager turtManager) {
		myLanguage = new PropertyUtility(languageDir+language.toString()+fileExtension);
		String[] commandLines = rawString.split(mySyntaxProp.getValue("Newline"));
		Queue<String> commandTokens = buildQueue(commandLines);
		NodeFactory nodeBuilder = new NodeFactory(turtManager);
		ArrayList<ExpressionNode> myNodes = new ArrayList<>();
		while (!commandTokens.isEmpty()) {
			myNodes.add(nodeBuilder.makeNode(commandTokens));
		}
		return myNodes;
	}

	private Queue<String> buildQueue(String[] commands) {
		Queue<String> commandQueue = new ArrayDeque<>();
		for (String line : commands) {
			String[] commandTokens = line.split(mySyntaxProp.getValue("Whitespace"));
			for (String token : commandTokens) {
				if (Pattern.matches(mySyntaxProp.getValue("Comment"), token)) break;
				String addString = token;
				if (Pattern.matches(mySyntaxProp.getValue("Command"), token)) {
					addString = myLanguage.getKey(token);
					if(addString == null){
						throw new ParserException(ParserException.INVALID_CMD,token);
					}
				}
				commandQueue.add(addString);
			}
		}
		return commandQueue;
	}

}
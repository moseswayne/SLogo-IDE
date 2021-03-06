package Model.parser;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

import Model.TurtleManager;
import Model.expressionTree.ExpressionNode;
import utils.Language;
import utils.PropertyUtility;

public class CommandParser {
	private final String syntaxFile = "Syntax.properties";
	private final String fileExtension = ".properties";
	private final String nline = "Newline";
	private final String space = "Whitespace";
	private final String comm = "Comment";
	private final String command = "Command";

	private PropertyUtility mySyntaxProp;
	private PropertyUtility myLanguage;

	public CommandParser() {
		mySyntaxProp = new PropertyUtility(syntaxFile);
	}

	public Queue<ExpressionNode> parse(String rawString, Language language, TurtleManager turtManager) {
		myLanguage = new PropertyUtility(language.toString()+fileExtension);
		String[] commandLines = rawString.split(mySyntaxProp.getValue(nline));
		Queue<String> commandTokens = buildQueue(commandLines);
		NodeFactory nodeBuilder = new NodeFactory(turtManager, mySyntaxProp);
		ArrayDeque<ExpressionNode> myNodes = new ArrayDeque<>();
		while (!commandTokens.isEmpty()) {
			myNodes.add(nodeBuilder.makeNode(commandTokens));
		}
		return myNodes;
	}

	private Queue<String> buildQueue(String[] commands) {
		Queue<String> commandQueue = new ArrayDeque<>();
		for (String line : commands) {
			String[] commandTokens = line.split(mySyntaxProp.getValue(space));
			for (String token : commandTokens) {
				if (Pattern.matches(mySyntaxProp.getValue(comm), token)) break;
				String addString = token;
				if (Pattern.matches(mySyntaxProp.getValue(command), token)) {
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
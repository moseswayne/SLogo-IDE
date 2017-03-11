package Model.backEndUtils;

import Model.TurtleManager;
import Model.expressionTree.ExpressionNode;
import Model.parser.CommandParser;

public class VariableIncrementer {
	private CommandParser myParser;
	private final String incrementName = "set ";
	
	public VariableIncrementer() {
		myParser = new CommandParser();
	}

	public ExpressionNode generateVar(String varName, Double value, ParameterObject params) {
			return myParser.parse(getMethodCall(varName, value), params.getSettings().getLanguage(), new TurtleManager()).poll();
	}
	
	private String getMethodCall(String variable, Double value) {
		return incrementName+variable+" "+value;
	}
}

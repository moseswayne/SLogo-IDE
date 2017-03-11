package Model.backEndUtils;

import Model.TurtleManager;
import Model.expressionTree.ExpressionNode;
import Model.parser.CommandParser;
import utils.Language;

public class VariableIncrementer {
	private CommandParser myParser;
	
	public VariableIncrementer() {
		myParser = new CommandParser();
	}

	public ExpressionNode generateVar(String varName, Double value, ParameterObject params) {
			return myParser.parse("set "+varName+" "+value, Language.English, new TurtleManager()).poll();
	}
	
	private String getMethodCall(String variable, Double value) {
		
	}
}

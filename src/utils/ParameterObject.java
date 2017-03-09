package utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import Model.TurtleModel;
import tree.CommandNode;

public class ParameterObject {

	private List<String> parameterList;
	private Map<String, Double> varMap;
	private TurtleModel myTurtle;
	private Stack<CommandNode> instructionStack;
	
	public ParameterObject(List<String> params, Map<String, Double> vars, TurtleModel turt) {
		this(params, vars);
		myTurtle = turt;
	}
	
	public ParameterObject(List<String> params, Map<String, Double> vars) {
		parameterList = params;
		varMap = vars;
	}
	
	public String getRawElement(int index) {
		try {
			return parameterList.get(index);
		} catch (Exception NullPointerException){
			//do something
		}
		return null;
	}
	
	public Double getDoubleAt(int index) {
		String val = getRawElement(index);
		if(varMap.containsKey(val)) {
			return varMap.get(val);
		} else {
			try {
				return Double.parseDouble(val);
			} catch (Exception NumberFormatException){
				//stuff
			}
		}
		return null;
	}
	
	public TurtleModel getTurtle() {
		return myTurtle;
	}
	
	public void setStack(Collection<CommandNode> instructs) {
		instructionStack = new Stack<CommandNode>();
		if(!instructs.isEmpty()) {
			for (CommandNode instruction:instructs) {
				instructionStack.push(instruction);
			}
		}
	}
	
	public Stack<CommandNode> getInstructions() {
		return instructionStack;
	}
}

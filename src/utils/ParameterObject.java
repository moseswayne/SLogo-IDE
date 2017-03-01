package utils;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ParameterObject {

	private List<String> parameterList;
	private Map<String, Double> varMap;
	private TurtleModel myTurtle;
	private Stack<String> instructionStack;
	
	public ParameterObject(List<String> params, Map<String, Double> vars, TurtleModel turt) {
		this(params, vars);
		myTurtle = turt;
	}
	
	public ParameterObject(List<String> params, Map<String, Double> vars) {
		parameterList = params;
		varMap = vars;
	}
	
	public Double getElement(int index) {
		try {
			String val = parameterList.get(index);
			if(varMap.containsKey(val)) {
				return varMap.get(val);
			} else {
				try {
					Double.parseDouble(val);
				} catch (Exception NumberFormatException){
					
				}
			}
		} catch (Exception NullPointerException){
			//do something
		}
		return null;
	}
	
	public TurtleModel getTurtle() {
		return myTurtle;
	}
	
	public void setStack(List<String> instructs) {
		instructionStack = new Stack<String>();
		for (String instruction:instructs) {
			instructionStack.push(instruction);
		}
	}
	
	public Stack<String> getInstructions() {
		return instructionStack;
	}
}

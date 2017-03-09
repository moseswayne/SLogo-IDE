package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Model.TurtleManager;
import Model.TurtleModel;
import tree.CommandNode;

public class ParameterObject implements Iterable<Double> {

	private List<String> parameterList;
	private Map<String, Double> varMap;
	private TurtleManager myTurtles;
	private TurtleModel myTurtle;
	private Stack<String> instructionStack;

	public ParameterObject(List<String> params, Map<String, Double> vars, TurtleModel turtle) {
		this(params, vars);
		myTurtle = turtle;
	}

	public ParameterObject(List<String> params, Map<String, Double> vars) {
		parameterList = params;
		varMap = vars;
	}

	public String getRawElement(int index) {
		try {
			return parameterList.get(index);
		} catch (Exception NullPointerException) {
			// do something
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

	/**
	 * Iterates over the double parameters Useful if there is an unknown number
	 * of inputs
	 * 
	 * @return
	 */
	@Override
	public Iterator<Double> iterator() {
		return new Iterator<Double>() {
			private final Iterator<String> paramsIter = parameterList.iterator();

			@Override
			public boolean hasNext() {
				return paramsIter.hasNext();
			}

			@Override
			public Double next() {
				return Double.parseDouble(paramsIter.next());
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("No removals allowed");
			}
		};
	}
}
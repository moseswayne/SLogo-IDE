package utils;

import java.util.Collection;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import Model.TurtleModel;
import tree.ExpressionNode;

public class ParameterObject implements Iterable<Double> {

	private List<String> parameterList;
	private Map<String, Double> varMap;
	private TurtleModel myTurtle;
	private Stack<ExpressionNode> instructionStack;

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
		if (varMap.containsKey(val)) {
			return varMap.get(val);
		} else {
			try {
				return Double.parseDouble(val);
			} catch (Exception NumberFormatException) {
				// stuff
			}
		}
		return null;
	}
	
	public void setTurtle(TurtleModel turt) {
		myTurtle = turt;
	}

	public TurtleModel getTurtle() {
		return myTurtle;
	}

	public void setStack(Collection<ExpressionNode> instructs) {
		instructionStack = new Stack<ExpressionNode>();
		if (!instructs.isEmpty()) {
			for (ExpressionNode instruction : instructs) {
				instructionStack.push(instruction);
			}
		}
	}

	public Stack<ExpressionNode> getInstructions() {
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
				String rawValue = nextString();
				Double retValue = null;
				if (varMap.containsKey(rawValue)) {
					retValue = varMap.get(rawValue);
				} else {
					try {
						retValue = Double.parseDouble(rawValue);
					} catch (Exception NumberFormatException) {
						// stuff
					}
				}
				return retValue;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("No removals allowed");
			}
			
			private String nextString() {
				return paramsIter.next();
			}
		};
	}
}
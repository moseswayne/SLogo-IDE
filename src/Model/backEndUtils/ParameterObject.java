package Model.backEndUtils;

import java.util.ArrayDeque; 
import java.util.Collection;

import java.util.Map;
import java.util.Queue;
import Model.TurtleModel;
import Model.expressionTree.ExpressionNode;

public class ParameterObject {

	private SLogoSettings mySettings;
	private Queue<String> parameterQueue;
	private Map<String, Double> varMap;
	private TurtleModel myTurtle;
	private Queue<ExpressionNode> instructionQueue;

	public ParameterObject(Collection<String> params, Map<String, Double> vars, SLogoSettings settings, TurtleModel turtle) {
		this(params, vars, settings);
		myTurtle = turtle;
	}

	public ParameterObject(Collection<String> params, Map<String, Double> vars, SLogoSettings settings) {
		mySettings = settings;
		parameterQueue = new ArrayDeque<String>();
		for(String parameter:params) {
			parameterQueue.add(parameter);
		}
		varMap = vars;
	}
	
	public void alterVar(String key, Double value) {
		varMap.put(key, value);
	}
	
	public boolean hasNext() {
		return (!parameterQueue.isEmpty());
	}
	
	public String nextRaw() {
		return parameterQueue.poll();
	}

	public String peekNextRaw() {
		return parameterQueue.peek();
	}
	
	public Double next() {
		Double retDouble = peekNext();
		parameterQueue.poll();
		return retDouble;
	}

	public Double peekNext() {
		String val = peekNextRaw();
		Double retValue = null;
		if (varMap.containsKey(val)) {
			retValue = varMap.get(val);
		} else {
			try {
				return Double.parseDouble(val);
			} catch (Exception NumberFormatException) {
				throw new ParameterException(ParameterException.INVALID_PAR, val);
			}
		}
		return retValue;
	}
	
	
	public void setTurtle(TurtleModel turt) {
		myTurtle = turt;
	}

	public TurtleModel getTurtle() {
		return myTurtle;
	}

	public void setStack(Collection<ExpressionNode> instructs) {
		instructionQueue = new ArrayDeque<ExpressionNode>();
		if (!instructs.isEmpty()) {
			for (ExpressionNode instruction : instructs) {
				instructionQueue.add(instruction);
			}
		}
	}

	public Queue<ExpressionNode> getInstructions() {
		return instructionQueue;
	}
	
	public SLogoSettings getSettings() {
		return mySettings;
	}
}
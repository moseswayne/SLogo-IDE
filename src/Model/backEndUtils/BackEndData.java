package Model.backEndUtils;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import Model.TurtleModel;
import Model.expressionTree.ExpressionNode;
import utils.TurtleParameters;

public class BackEndData {

	private Double myValue;
	private Queue<ExpressionNode> instructionStack;
	private Queue<TurtleParameters> myTurtleParameters;

	public BackEndData() {
		instructionStack = new ArrayDeque<ExpressionNode>();
		myTurtleParameters = new ArrayDeque<TurtleParameters>();
	}

	public void setValue(double thisValue) {
		myValue = thisValue;
	}

	public Double getMyValue() {
		return myValue;
	}

	public void addTurtleParameters(TurtleModel turtle) {
		myTurtleParameters.add(new TurtleParameters(turtle));
	}

	public boolean hasNextTurtleParameter() {
		return (!myTurtleParameters.isEmpty());
	}

	public TurtleParameters transferParameter() {
		return myTurtleParameters.poll();
	}

	public void setInstructions(ExpressionNode instruction) {
		instructionStack.add(instruction);
	}

	public boolean hasNextInstruction() {
		return (!instructionStack.isEmpty());
	}

	public Queue<ExpressionNode> getInstructions() {
		Queue<ExpressionNode> instructionQueue = new ArrayDeque<>();
		while (hasNextInstruction()) {
			instructionQueue.add(instructionStack.poll());
		}
		return instructionQueue;
	}
}

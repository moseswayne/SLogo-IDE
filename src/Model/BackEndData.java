package Model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import tree.ExpressionNode;
import utils.TurtleParameters;

public class BackEndData {

	private Double myValue;
	private Stack<ExpressionNode> instructionStack;
	private Queue<TurtleParameters> myTurtleParameters;

	public BackEndData() {
		instructionStack = new Stack<ExpressionNode>();
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
		instructionStack.push(instruction);
	}

	public boolean hasNextInstruction() {
		return (!instructionStack.isEmpty());
	}

	public Queue<ExpressionNode> getInstructions() {
		Queue<ExpressionNode> instructionQueue = new ArrayDeque<>();
		while (hasNextInstruction()) {
			instructionQueue.add(instructionStack.pop());
		}
		return instructionQueue;
	}
}

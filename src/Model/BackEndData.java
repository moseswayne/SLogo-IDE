package Model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import tree.CommandNode;
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
	
	public void addTurtleParameters(double x, double y, double heading, boolean isPendown, boolean turtleShow) {
		myTurtleParameters.add(new TurtleParameters(0, x, y, heading, isPendown, turtleShow, turtleShow));
	}
	
	public Queue<TurtleParameters> transferQueue() {
		return myTurtleParameters;
	}
	
	public void setInstructions(ExpressionNode instruction) {
		instructionStack.push(instruction);
	}
	
	public boolean hasNextInstruction() {
		return (!instructionStack.isEmpty());
	}
	
	public Queue<ExpressionNode> getInstructions() {
		Queue<ExpressionNode> instructionQueue = new ArrayDeque<>();
		while(hasNextInstruction()) {
			instructionQueue.add(instructionStack.pop());
		}
		return instructionQueue;
	}
}

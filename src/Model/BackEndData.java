package Model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import tree.CommandNode;
import utils.TurtleParameters;

public class BackEndData {

	private Double myValue;
	private Stack<CommandNode> instructionStack;
	private Queue<TurtleParameters> myTurtleParameters;
	
	public BackEndData() {
		instructionStack = new Stack<CommandNode>();
		myTurtleParameters = new ArrayDeque<TurtleParameters>();
	}
	
	public void setValue(double thisValue) {
		myValue = thisValue;
	}
	
	public Double getMyValue() {
		return myValue;
	}
	
	public void addTurtleParameters(double x, double y, double heading, boolean isPendown, boolean turtleShow) {
		myTurtleParameters.add(new TurtleParameters(x, y, heading, isPendown, turtleShow));
	}
	
	public Queue<TurtleParameters> transferQueue() {
		return myTurtleParameters;
	}
	
	public void setInstructions(CommandNode instruction) {
		instructionStack.push(instruction);
	}
	
	public boolean hasNextInstruction() {
		return (!instructionStack.isEmpty());
	}
	
	public CommandNode getNextInstruction() {
		return instructionStack.pop();
	}
}

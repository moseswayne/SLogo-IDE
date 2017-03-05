package Model;

import java.util.Queue;
import java.util.Stack;

import utils.TurtleParameters;

public class BackEndData {

	private Double myValue;
	private Stack<String> instructionStack;
	private Queue<TurtleParameters> myTurtleParameters;
	
	public BackEndData() {
		
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
	
	public void setInstructions(String instruction) {
		instructionStack.push(instruction);
	}
	
	public boolean hasNextInstruction() {
		return (!instructionStack.isEmpty());
	}
	
	public String getNextInstruction() {
		return instructionStack.pop();
	}
}

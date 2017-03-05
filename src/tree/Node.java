package tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import Operations.CommandOperation;

public class Node {

	private Stack<Node> myChildren;
	private Double myValue;
	private CommandOperation myOperation;
	
	public Node(Collection<Node> nodeChildren) {
		myChildren = new Stack<Node>();
		Iterator<Node> iter = nodeChildren.iterator();
		while(iter.hasNext()) {
			myChildren.push(iter.next());
		}
	}
	
	public void addChild(Node node) {
		myChildren.push(node);
	}
	
	public boolean hasNextChild() {
		return (!myChildren.isEmpty());
	}
	
	public Node getNextChild() {
		return myChildren.pop();
	}
	
	public void setMyValue(double newValue) {
		myValue = newValue;
	}
	
	public double getValue() {
		return myValue;
	}
	
	public CommandOperation getOp() {
		return myOperation;
	}
}

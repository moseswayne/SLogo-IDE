package controller;

import java.util.List;

public abstract class Node {

	protected List<Node> myChildren;
	
	protected Node(List<Node> nodeChildren) {
		myChildren = nodeChildren;
	}
	
	public void addChild(Node node) {
		myChildren.add(node);
	}
}

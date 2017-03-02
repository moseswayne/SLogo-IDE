package controller;

import java.util.List;
import java.util.ArrayList;

public abstract class Node {

	protected List<Node> myChildren;
	
	protected Node() {
		myChildren = new ArrayList<Node>();
	}
	
	protected Node(List<Node> nodeChildren) {
		myChildren = nodeChildren;
	}
	
	public void addChild(Node node) {
		myChildren.add(node);
	}
}

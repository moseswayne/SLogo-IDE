package controller;

import java.util.List;
import java.util.ArrayList;

public abstract class A_Node {

	protected List<A_Node> myChildren;
	
	protected A_Node() {
		myChildren = new ArrayList<A_Node>();
	}
	
	protected A_Node(List<A_Node> nodeChildren) {
		myChildren = nodeChildren;
	}
	
	public void addChild(A_Node node) {
		myChildren.add(node);
	}
}

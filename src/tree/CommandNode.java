package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

import Model.BackEndData;
import Operations.CommandOperation;
import utils.ParameterObject;

public abstract class CommandNode implements ExpressionNode{

	private Queue<ExpressionNode> myChildren;
	private CommandOperation myOperation;
	
	public CommandNode() {
		myChildren = new ArrayDeque<ExpressionNode>();
	}
	
	public void addChild(ExpressionNode child) {
		myChildren.add(child);
	}
	
	protected ParameterObject getParameters(BackEndData data, Map<String, Double> vars) {
		ArrayList<String> paramList = new ArrayList<String>();
		while(!myChildren.isEmpty()) {
			paramList.add(myChildren.poll().getValue(data, vars));
		}
		return new ParameterObject(paramList, vars);
	}
	
	protected CommandOperation getOp() {
		return myOperation;
	}
	
	public void setOp(CommandOperation op) {
		myOperation = op;
	}
	
	protected void cloneContents(CommandNode node) {
		node.setOp(myOperation);
		for (ExpressionNode child:myChildren) {
			node.addChild(child.cloneNode());
		}
	}
}

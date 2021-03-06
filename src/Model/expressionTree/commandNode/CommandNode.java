package Model.expressionTree.commandNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.backEndUtils.SLogoSettings;
import Model.expressionTree.ExpressionNode;
import Model.operations.CommandOperation;

public abstract class CommandNode implements ExpressionNode{

	private Queue<ExpressionNode> myChildren;
	private CommandOperation myOperation;
	
	public CommandNode() {
		myChildren = new ArrayDeque<ExpressionNode>();
	}
	
	/**
	 * Note: must be added in the exact parsing order
	 * @param child - expression node child of this command node
	 */
	public void addChild(ExpressionNode child) {
		myChildren.add(child);
	}
	
	protected ParameterObject getParameters(BackEndData data, Map<String, Double> vars, SLogoSettings settings) {
		ArrayList<String> paramList = new ArrayList<String>();
		while(!myChildren.isEmpty()) {
			paramList.add(myChildren.poll().getValue(data, vars, settings));
		}
		return new ParameterObject(paramList, vars, settings);
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

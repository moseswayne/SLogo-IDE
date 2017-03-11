package Model.expressionTree.commandNode;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.backEndUtils.SLogoSettings;
import Model.expressionTree.ExpressionNode;

public class ControlCommandNode extends CommandNode{

	private Queue<ExpressionNode> repeatChildren;
	
	public ControlCommandNode() {
		super();
		repeatChildren = new ArrayDeque<ExpressionNode>();
	}
	
	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap, SLogoSettings settings) {
		getOp().execute(getModifiedParameters(startData, varMap, settings), startData);
		for(ExpressionNode command:startData.getInstructions()) {
			command.getValue(startData, varMap, settings);
		}
		return startData.getMyValue().toString();
	}
	
	public void addLoopInstruction(ExpressionNode node) {
		repeatChildren.add(node);
	}
	
	private ParameterObject getModifiedParameters(BackEndData data, Map<String, Double> vars, SLogoSettings settings) {
		ParameterObject controlParameters = getParameters(data, vars, settings);
		controlParameters.setStack(repeatChildren);
		return controlParameters;
	}

	@Override
	public ExpressionNode cloneNode() {
		ControlCommandNode thisNode = new ControlCommandNode();
		cloneContents(thisNode);
		for (ExpressionNode child:repeatChildren) {
			thisNode.addLoopInstruction(child.cloneNode());
		}
		return thisNode;
	}
}

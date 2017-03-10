package tree;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;

public class ControlCommandNode extends CommandNode{

	private Queue<ExpressionNode> repeatChildren;
	
	public ControlCommandNode() {
		super();
		repeatChildren = new ArrayDeque<ExpressionNode>();
	}
	
	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		getOp().execute(getModifiedParameters(startData, varMap), startData);
		for(ExpressionNode command:startData.getInstructions()) {
			command.getValue(startData, varMap);
		}
		return startData.getMyValue().toString();
	}
	
	public void addLoopInstruction(ExpressionNode node) {
		repeatChildren.add(node);
	}
	
	private ParameterObject getModifiedParameters(BackEndData data, Map<String, Double> vars) {
		ParameterObject controlParameters = getParameters(data, vars);
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

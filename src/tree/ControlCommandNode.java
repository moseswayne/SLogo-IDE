package tree;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import Model.BackEndData;
import utils.ParameterObject;

public class ControlCommandNode extends CommandNode{

	private Queue<ExpressionNode> repeatChildren;
	
	public ControlCommandNode() {
		super();
		repeatChildren = new ArrayDeque<ExpressionNode>();
	}
	
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		getOp().execute(getModifiedParameters(startData, varMap), startData);
		for(ExpressionNode command:startData.getInstructions()) {
			command.getValue(startData, varMap);
		}
		return startData.getMyValue().toString();
	}
	
	public void addLoopInstruction(ExpressionNode node) {
		/*Iterator<CommandNode> iter = nodes.iterator();
		while(iter.hasNext()) {
			repeatChildren.add(iter.next());
		}*/
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

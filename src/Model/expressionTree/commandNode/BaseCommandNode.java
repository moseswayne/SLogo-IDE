package Model.expressionTree.commandNode;

import java.util.Map;

import Model.backEndUtils.BackEndData;
import Model.expressionTree.ExpressionNode;

public class BaseCommandNode extends CommandNode{

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		getOp().execute(getParameters(startData, varMap), startData);
		return startData.getMyValue().toString();
	}

	@Override
	public ExpressionNode cloneNode() {
		BaseCommandNode thisNode = new BaseCommandNode();
		cloneContents(thisNode);
		return thisNode;
	}

}

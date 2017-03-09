package tree;

import java.util.Map;

import Model.BackEndData;

public class BaseCommandNode extends CommandNode{

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		getOp().execute(getParameters(startData, varMap), startData);
		System.out.println(startData.getMyValue().toString());
		return startData.getMyValue().toString();
	}

	@Override
	public ExpressionNode cloneNode() {
		BaseCommandNode thisNode = new BaseCommandNode();
		cloneContents(thisNode);
		return thisNode;
	}

}

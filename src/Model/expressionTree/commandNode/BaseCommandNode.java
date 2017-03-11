package Model.expressionTree.commandNode;

import java.util.Map;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.expressionTree.ExpressionNode;

public class BaseCommandNode extends CommandNode{

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap, SLogoSettings settings) {
		getOp().execute(getParameters(startData, varMap, settings), startData);
		return startData.getMyValue().toString();
	}

	@Override
	public ExpressionNode cloneNode() {
		BaseCommandNode thisNode = new BaseCommandNode();
		cloneContents(thisNode);
		return thisNode;
	}

}

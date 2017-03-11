package Model.expressionTree;

import java.util.Map;

import Model.backEndUtils.BackEndData;

public interface ExpressionNode {

	public String getValue(BackEndData startData, Map<String, Double> varMap);
	
	public ExpressionNode cloneNode();
}

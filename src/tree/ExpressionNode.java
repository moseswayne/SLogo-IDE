package tree;

import java.util.Map;

import Model.BackEndData;

public interface ExpressionNode {

	public String getValue(BackEndData startData, Map<String, Double> varMap);
	
	public ExpressionNode cloneNode();
}

package Model.expressionTree;

import java.util.Map;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;

public interface ExpressionNode {

	public String getValue(BackEndData startData, Map<String, Double> varMap, SLogoSettings settings);
	
	public ExpressionNode cloneNode();
}

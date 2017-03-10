package tree;

import java.util.Map;

import Model.backEndUtils.BackEndData;

public class LeafNode implements ExpressionNode{

	private String myValue;
	
	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		return myValue;
	}
	
	public void setMyValue(String newValue) {
		myValue = newValue;
	}

	@Override
	public ExpressionNode cloneNode() {
		LeafNode thisNode = new LeafNode();
		thisNode.setMyValue(myValue);
		return thisNode;
	}
}

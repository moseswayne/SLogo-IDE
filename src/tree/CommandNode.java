package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import Model.BackEndData;
import Operations.CommandOperation;
import utils.ParameterObject;

public class CommandNode {

	private BackEndData myData;
	private Stack<CommandNode> myChildren;
	private String myValue;
	private CommandOperation myOperation;
	private Map<String, Double> myVariables;
	
	public CommandNode(Collection<CommandNode> nodeChildren) {
		myChildren = new Stack<CommandNode>();
		Iterator<CommandNode> iter = nodeChildren.iterator();
		while(iter.hasNext()) {
			myChildren.push(iter.next());
		}
	}
	
	public BackEndData initiateExpression(BackEndData startData, Map<String, Double> varMap) {
		myData = startData;
		myOperation.execute(getParameters(startData, varMap), startData);
		return myData;
	}
	
	public void addChild(CommandNode node) {
		myChildren.push(node);
	}
	
	private ParameterObject getParameters(BackEndData data, Map<String, Double> vars) {
		ArrayList<String> paramList = new ArrayList<String>();
		while(!myChildren.isEmpty()) {
			paramList.add(myChildren.pop().getValue(data, vars));
		}
		return (new ParameterObject(paramList, vars));
	}
	
	public void setMyValue(String newValue) {
		myValue = newValue;
	}
	
	public String getValue(BackEndData data, Map<String, Double> vars) {
		if (myValue == null) {
			getOp().execute(getParameters(data, vars), data);
			setMyValue(data.getMyValue().toString());
		}
		return myValue;
	}
	
	public void setOp(CommandOperation op) {
		myOperation = op;
	}
	
	private CommandOperation getOp() {
		return myOperation;
	}
	
}

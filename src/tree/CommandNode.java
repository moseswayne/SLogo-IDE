package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import Model.BackEndData;
import Operations.CommandOperation;
import utils.ParameterObject;

public class CommandNode {

	private BackEndData myData;
	private Queue<CommandNode> myChildren;
	private Stack<CommandNode> repeatChildren;
	private String myValue;
	private CommandOperation myOperation;
	
	public CommandNode(Collection<CommandNode> nodeChildren) {
		myChildren = new ArrayDeque<CommandNode>();
		repeatChildren = new Stack<CommandNode>();
		Iterator<CommandNode> iter = nodeChildren.iterator();
		while(iter.hasNext()) {
			myChildren.add(iter.next());
		}
	}
	
	public void addChild(CommandNode child) {
		myChildren.add(child);
	}
	
	public BackEndData initiateExpression(BackEndData startData, Map<String, Double> varMap) {
		myData = startData;
		getValue(startData, varMap);
		return myData;
	}
	
	private ParameterObject getParameters(BackEndData data, Map<String, Double> vars) {
		ArrayList<String> paramList = new ArrayList<String>();
		while(!myChildren.isEmpty()) {
			paramList.add(myChildren.poll().getValue(data, vars));
		}
		ParameterObject retParameters = new ParameterObject(paramList, vars);
		retParameters.setStack(repeatChildren);
		return retParameters;
	}
	
	public void setMyValue(String newValue) {
		myValue = newValue;
	}
	
	private String getValue(BackEndData data, Map<String, Double> vars) {
		if (myValue == null) {
			myOperation.execute(getParameters(data, vars), data);
			while(data.hasNextInstruction()) {
				data.getNextInstruction().initiateExpression(data, vars);
			}
			setMyValue(data.getMyValue().toString());
		}
		System.out.println(myValue);
		return myValue;
	}
	
	public void setOp(CommandOperation op) {
		myOperation = op;
	}
	
}

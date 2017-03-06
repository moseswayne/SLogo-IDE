package testPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.BackEndData;
import Operations.CommandOperation;
import mathOps.Add;
import tree.CommandNode;

public class tester {
	
	public static void main(String args[]) {
		tester myTester = new tester();
		CommandOperation sum = new Add();
		ArrayList<CommandNode> myNodes = new ArrayList<>();
		myNodes.add(myTester.makeDifLeaf("x"));
		myNodes.add(myTester.makeLeaf(3.0));
		ArrayList<CommandNode> otherNodes = new ArrayList<>();
		otherNodes.add(myTester.makeLeaf(5.0));
		otherNodes.add(myTester.makeLeaf(6.0));
		CommandNode a = myTester.makeBranch(sum, myNodes);
		CommandNode b = myTester.makeBranch(sum, otherNodes);
		ArrayList<CommandNode> finalNodes = new ArrayList<>();
		finalNodes.add(a);
		finalNodes.add(b);
		CommandNode c = new CommandNode(finalNodes);
		c.setOp(sum);
		BackEndData myData = new BackEndData();
		Map<String, Double> varMap = new HashMap<>();
		varMap.put("x", 5.0);
		c.initiateExpression(myData, varMap);
		System.out.println(myData.getMyValue());
	}
	
	private CommandNode makeDifLeaf(String myKey) {
		CommandNode leafNode = new CommandNode(new ArrayList<CommandNode>());
		leafNode.setMyValue(myKey);
		return leafNode;
	}

	public CommandNode makeLeaf(Double val) {
		CommandNode leafNode = new CommandNode(new ArrayList<CommandNode>());
		leafNode.setMyValue(val.toString());
		return leafNode;
	}
	
	public CommandNode makeBranch(CommandOperation myOp, List<CommandNode> myLeaves) {
		CommandNode branchNode = new CommandNode(myLeaves);
		branchNode.setOp(myOp);
		return branchNode;
	}
}

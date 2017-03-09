package testPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.BackEndData;
import Operations.CommandOperation;
import controlOps.Repeat;
import mathOps.Sum;
import tree.CommandNode;

public class tester {
	
	public static void main(String args[]) {
		tester myTester = new tester();
		CommandOperation sum = new Sum();
		ArrayList<CommandNode> myNodes = new ArrayList<>();
		myNodes.add(myTester.makeDifLeaf("x"));
		myNodes.add(myTester.makeLeaf(3.0));
		ArrayList<CommandNode> otherNodes = new ArrayList<>();
		otherNodes.add(myTester.makeLeaf(5.0));
		otherNodes.add(myTester.makeLeaf(6.0));
		CommandNode a = myTester.makeBranch(sum, myNodes);
		CommandNode b = myTester.makeBranch(sum, otherNodes);
		CommandNode c = new CommandNode();
		c.addChild(a);
		c.addChild(b);
		c.setOp(sum);
		BackEndData myData = new BackEndData();
		Map<String, Double> varMap = new HashMap<>();
		varMap.put("x", 5.0);
		CommandOperation repeat = new Repeat();
		CommandNode d = new CommandNode();
		ArrayList<CommandNode> randList = new ArrayList<>();
		randList.add(c);
		d.setOp(repeat);
		d.addLoopInstructions(randList);
		CommandNode e = myTester.makeLeaf(2.0);
		d.addChild(e);
		d.initiateExpression(myData, varMap);
		//System.out.println(myData.getMyValue());
	}
	
	private CommandNode makeDifLeaf(String myKey) {
		CommandNode leafNode = new CommandNode();
		leafNode.setMyValue(myKey);
		return leafNode;
	}

	public CommandNode makeLeaf(Double val) {
		CommandNode leafNode = new CommandNode();
		leafNode.setMyValue(val.toString());
		return leafNode;
	}
	
	public CommandNode makeBranch(CommandOperation myOp, List<CommandNode> myLeaves) {
		CommandNode branchNode = new CommandNode();
		for(CommandNode leaf : myLeaves) {
			branchNode.addChild(leaf);
		}
		branchNode.setOp(myOp);
		return branchNode;
	}
}

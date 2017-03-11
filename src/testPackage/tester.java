package testPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.backEndUtils.BackEndData;
import Model.expressionTree.ExpressionNode;
import Model.expressionTree.LeafNode;
import Model.expressionTree.commandNode.BaseCommandNode;
import Model.expressionTree.commandNode.CommandNode;
import Model.expressionTree.commandNode.ControlCommandNode;
import Model.operations.CommandOperation;
import Model.operations.commandOps.basicOps.Sum;
import Model.operations.commandOps.controlOps.Repeat;

public class tester {
	
	public static void main(String args[]) {
		tester myTester = new tester();
		CommandOperation sum = new Sum();
		ExpressionNode leafA = myTester.makeLeaf("x");
		ExpressionNode leafB = myTester.makeLeaf("3.0");
		ExpressionNode leafC = myTester.makeLeaf("5.0");
		ExpressionNode leafD = myTester.makeLeaf("6.0");
		BaseCommandNode a = new BaseCommandNode();
		a.setOp(sum);
		a.addChild(leafA);
		a.addChild(leafB);
		BaseCommandNode b = new BaseCommandNode();
		b.setOp(sum);
		b.addChild(leafC);
		b.addChild(leafD);
		BaseCommandNode c = new BaseCommandNode();
		c.addChild(a);
		c.addChild(b);
		c.setOp(sum);
		BackEndData myData = new BackEndData();
		Map<String, Double> varMap = new HashMap<>();
		varMap.put("x", 5.0);
		//System.out.println(c.getValue(myData, varMap));
		CommandOperation repeat = new Repeat();
		ControlCommandNode d = new ControlCommandNode();
		d.setOp(repeat);
		d.addLoopInstruction(c);
		ExpressionNode leafE = myTester.makeLeaf("10.0");
		d.addChild(leafE);
		d.getValue(myData, varMap);
		//System.out.println(myData.getMyValue());
	}

	public ExpressionNode makeLeaf(String val) {
		LeafNode leafNode = new LeafNode();
		leafNode.setMyValue(val);
		return leafNode;
	}
}

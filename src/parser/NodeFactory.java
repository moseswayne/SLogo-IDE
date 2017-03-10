package parser;

import java.lang.reflect.Method;
import java.util.Queue;

import Model.TurtleManager;
import tree.BaseCommandNode;
import tree.CommandNode;
import tree.ControlCommandNode;
import tree.ExpressionNode;
import tree.LeafNode;
import tree.TurtleCommandNode;
import utils.PropertyUtility;

public class NodeFactory {
	private final String typeFile = "Type.properties";
	private final String argFile = "Arguments.properties";
	private final String methodStub = "make";
	private final String opDir = "Model.operations.commandOps.";
	private final String opEnd = "Ops.";

	private PropertyUtility myTypes;
	private PropertyUtility myArguments;
	private TurtleManager turtManager;
	private OperationFactory myOpFactory;

	public NodeFactory(TurtleManager turt) {
		turtManager = turt;
		myTypes = new PropertyUtility(typeFile);
		myArguments = new PropertyUtility(argFile);
		myOpFactory = new OperationFactory();
	}

	public ExpressionNode makeNode(Queue<String> remainingTokens) {
		String type = myTypes.getKey(remainingTokens.peek());
		System.out.println(remainingTokens.peek());
		if (type == null) {
			throw new ParserException(ParserException.INVALID_VAR, type);
		}
		/*
		try {
			Method constructNode = this.getClass().getDeclaredMethod(methodStub + type);
			constructNode.setAccessible(true);
			return (ExpressionNode) constructNode.invoke(this, remainingTokens);
		} catch (Exception e) {
			throw new ParserException(ParserException.INVALID_CMD, type);
		}*/
		if(type.equals("Control")) {
			return makeControl(remainingTokens, type);
		} else if(type.equals("Turtle")) {
			return makeTurtle(remainingTokens, type);
		} else if(type.equals("Basic")) {
			return makeBasic(remainingTokens, type);
		} 
		return makeLeaf(remainingTokens, type);
	}

	private ExpressionNode makeLeaf(Queue<String> remainingTokens, String type) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}

	private ExpressionNode makeTurtle(Queue<String> remainingTokens, String type) {
		String command = remainingTokens.poll();
		TurtleCommandNode newTurt = new TurtleCommandNode(turtManager);
		addBaseVariablesLoop(newTurt,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens,type);
		return newTurt;
	}

	private ExpressionNode makeBasic(Queue<String> remainingTokens, String type) {
		String command = remainingTokens.poll();
		BaseCommandNode newBase = new BaseCommandNode();
		addBaseVariablesLoop(newBase,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens,type);
		return newBase;
	}

	private ExpressionNode makeControl(Queue<String> remainingTokens, String type) {
		String command = remainingTokens.poll();
		ControlCommandNode newControl = new ControlCommandNode();
		if(remainingTokens.peek().equals("[")) remainingTokens.poll();
		addBaseVariablesLoop(newControl,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens,type);
		if(remainingTokens.peek().equals("]")) remainingTokens.poll();
		return newControl;
	}
	
	private void addBaseVariablesLoop(CommandNode node, String opName, int numArgs, Queue<String> remainingTokens, String commandPackage) {
		node.setOp(myOpFactory.getOp(opName, opDir+commandPackage.toLowerCase()+opEnd));
		for (int i = 0; i < numArgs; i++) {
			node.addChild(makeNode(remainingTokens));
		}
	}
}

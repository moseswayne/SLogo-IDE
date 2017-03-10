package controller;

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
		if(type.equals("Leaf")) {
			return makeLeaf(remainingTokens);
		} else if(type.equals("Turtle")) {
			return makeTurtle(remainingTokens);
		} else if(type.equals("Base")) {
			return makeBase(remainingTokens);
		} 
		return makeControl(remainingTokens);
	}

	private ExpressionNode makeLeaf(Queue<String> remainingTokens) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}

	private ExpressionNode makeTurtle(Queue<String> remainingTokens) {
		String command = remainingTokens.poll();
		TurtleCommandNode newTurt = new TurtleCommandNode(turtManager);
		addBaseVariablesLoop(newTurt,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens);
		return newTurt;
	}

	private ExpressionNode makeBase(Queue<String> remainingTokens) {
		String command = remainingTokens.poll();
		BaseCommandNode newBase = new BaseCommandNode();
		addBaseVariablesLoop(newBase,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens);
		return newBase;
	}

	private ExpressionNode makeControl(Queue<String> remainingTokens) {
		String command = remainingTokens.poll();
		ControlCommandNode newControl = new ControlCommandNode();
		if(remainingTokens.peek().equals("[")) remainingTokens.poll();
		addBaseVariablesLoop(newControl,command,Integer.parseInt(myArguments.getValue(command)),remainingTokens);
		if(remainingTokens.peek().equals("]")) remainingTokens.poll();
		return newControl;
	}
	
	private void addBaseVariablesLoop(CommandNode node, String opName, int numArgs, Queue<String> remainingTokens) {
		node.setOp(myOpFactory.getOp(opName));
		for (int i = 0; i < numArgs; i++) {
			node.addChild(makeNode(remainingTokens));
		}
	}
}

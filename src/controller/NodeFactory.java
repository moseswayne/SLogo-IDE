package controller;

import java.lang.reflect.Method;
import java.util.Queue;

import Model.TurtleManager;
import tree.ExpressionNode;
import tree.LeafNode;
import tree.TurtleCommandNode;
import utils.PropertyUtility;

public class NodeFactory {
	private final String typeFile = "resources/Type.properties";
	private final String argFile = "resources/Arguments.properties";
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
		if(type==null) {
			throw new ParserException(ParserException.INVALID_VAR,type);
		}
		try {
			Method constructNode = this.getClass().getDeclaredMethod(methodStub+type);
			constructNode.setAccessible(true);
			return (ExpressionNode) constructNode.invoke(this, remainingTokens);
		} catch (Exception e) {
			throw new ParserException(ParserException.INVALID_CMD,type);
		}
	}
	
	private ExpressionNode makeLeaf(Queue<String> remainingTokens) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}
	
	private ExpressionNode makeTurtle(Queue<String> remainingTokens) {
		TurtleCommandNode newTurt = new TurtleCommandNode(turtManager);
		newTurt.setOp(myOpFactory.getOp(remainingTokens.poll()));
		return newTurt;
	}
	
	private ExpressionNode makeBase(Queue<String> remainingTokens) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}
	
	private ExpressionNode makeControl(Queue<String> remainingTokens) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}
}

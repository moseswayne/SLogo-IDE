package Model.parser;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

import Model.TurtleManager;
import Model.expressionTree.ExpressionNode;
import Model.expressionTree.LeafNode;
import Model.expressionTree.commandNode.BaseCommandNode;
import Model.expressionTree.commandNode.CommandNode;
import Model.expressionTree.commandNode.ControlCommandNode;
import Model.expressionTree.commandNode.TurtleCommandNode;
import utils.PropertyUtility;

public class NodeFactory {
	private final String typeFile = "Type.properties";
	private final String argFile = "Arguments.properties";
	private final String methodStub = "make";
	private final String opDir = "Model.operations.commandOps.";
	private final String opEnd = "Ops.";

	private PropertyUtility myTypes;
	private PropertyUtility myArguments;
	private PropertyUtility syntaxProp;
	private TurtleManager turtManager;
	private OperationFactory myOpFactory;

	public NodeFactory(TurtleManager turt, PropertyUtility syntax) {
		turtManager = turt;
		myTypes = new PropertyUtility(typeFile);
		myArguments = new PropertyUtility(argFile);
		myOpFactory = new OperationFactory();
		syntaxProp = syntax;
	}

	public ExpressionNode makeNode(Queue<String> remainingTokens) {
		TriFunction<Integer,Integer,Queue<String>,Boolean> eval = checkSyntacticSugar(remainingTokens);
		String type = myTypes.getKey(remainingTokens.peek());
		System.out.println(remainingTokens.peek());
		if (type == null) {
			throw new ParserException(ParserException.INVALID_VAR, type);
		}
		
		try {
			Method constructNode = this.getClass().getDeclaredMethod(methodStub + type, new Class[] {Queue.class, String.class, TriFunction.class});
			constructNode.setAccessible(true);
			Object[] parameters = {remainingTokens, type, eval};
			return (ExpressionNode) constructNode.invoke(this, parameters);
		} catch (Exception e) {
			throw new ParserException(ParserException.INVALID_CMD, type);
		}
	}

	private ExpressionNode makeLeaf(Queue<String> remainingTokens, String type, TriFunction<Integer,Integer,Queue<String>,Boolean> evaluation) {
		LeafNode newLeaf = new LeafNode();
		newLeaf.setMyValue(remainingTokens.poll());
		return newLeaf;
	}

	private ExpressionNode makeTurtle(Queue<String> remainingTokens, String type, TriFunction<Integer,Integer,Queue<String>,Boolean> evaluation) {
		String command = remainingTokens.poll();
		TurtleCommandNode newTurt = new TurtleCommandNode(turtManager);
		addBaseVariablesLoop(newTurt,command,remainingTokens,type,evaluation);
		return newTurt;
	}

	private ExpressionNode makeBasic(Queue<String> remainingTokens, String type, TriFunction<Integer,Integer,Queue<String>,Boolean> evaluation) {
		String command = remainingTokens.poll();
		BaseCommandNode newBase = new BaseCommandNode();
		addBaseVariablesLoop(newBase,command,remainingTokens,type, evaluation);
		return newBase;
	}

	private ExpressionNode makeControl(Queue<String> remainingTokens, String type, TriFunction<Integer,Integer,Queue<String>,Boolean> evaluation) {
		String command = remainingTokens.poll();
		ControlCommandNode newControl = new ControlCommandNode();
		if(remainingTokens.peek().equals("[")) remainingTokens.poll();
		addBaseVariablesLoop(newControl,command,remainingTokens,type, evaluation);
		if(remainingTokens.peek().equals("]")) remainingTokens.poll();
		
		return newControl;
	}
	
	private void addBaseVariablesLoop(CommandNode node, String opName, Queue<String> remainingTokens, String commandPackage, TriFunction<Integer,Integer,Queue<String>,Boolean> evaluation) {
		node.setOp(myOpFactory.getOp(opName, opDir+commandPackage.toLowerCase()+opEnd));
		int numArgs = Integer.parseInt(myArguments.getValue(opName));
		int count = 0;
		while(evaluation.apply(count, numArgs, remainingTokens)) {
			node.addChild(makeNode(remainingTokens));
			count++;
		}
	}
	
	private TriFunction<Integer,Integer,Queue<String>,Boolean> checkSyntacticSugar(Queue<String> remainingTokens) {
		if(syntaxProp.getKey(remainingTokens.peek()).equals("GroupStart")) {
			remainingTokens.poll();
			return  (count,numAgrs,tokensRemaining) -> {
				if (syntaxProp.getKey(tokensRemaining.peek()).equals("GroupEnd")){
					tokensRemaining.poll();
					return false;
				}
				return true;};
		}
		return (count,numArgs,tokensRemaining) -> {return (count<numArgs);};
	}
	
	@FunctionalInterface
	public interface TriFunction<var1, var2, var3, res> {
		public res apply(var1 i, var2 j, var3 k);
	}
}

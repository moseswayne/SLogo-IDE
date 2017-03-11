package Model.operations.commandOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Model.TurtleManager;
import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.expressionTree.ExpressionNode;
import Model.expressionTree.commandNode.CommandNode;
import Model.operations.CommandOperation;
import Model.parser.CommandParser;
import View.viewUtils.FrontEndData;
import utils.Language;

public abstract class A_StructureCommand implements CommandOperation {

	private List<ExpressionNode> instructionList;
	private BackEndData myData;

	@Override
	public void execute(ParameterObject params, BackEndData data) {
		myData = data;
		unStackList(params.getInstructions());
		modifyInstructionStack(params);
	}
	
	protected abstract void modifyInstructionStack(ParameterObject params);
	
	private void unStackList(Queue<ExpressionNode> inStack) {
		instructionList = new ArrayList<ExpressionNode>();
		while(!inStack.isEmpty()) {
			instructionList.add(inStack.poll());
		}
	}
	
	protected void addNodeStack(ExpressionNode node) {
		myData.setInstructions(node);
	}
	
	protected void addListStack() {
		for (ExpressionNode instruction:instructionList) {
			myData.setInstructions(instruction.cloneNode());
		}
	}
	
	protected ExpressionNode initializeIncrementVariable(String varName, Double value, ParameterObject params) {
		CommandParser parseNode = new CommandParser();
		return parseNode.parse("set "+varName+" "+value, Language.English, new TurtleManager()).poll();
	}
	
}

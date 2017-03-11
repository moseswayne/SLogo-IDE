package Model.operations.commandOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.backEndUtils.VariableIncrementer;
import Model.expressionTree.ExpressionNode;
import Model.operations.CommandOperation;

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
		VariableIncrementer vi = new VariableIncrementer();
		return vi.generateVar(varName, value, params);
	}
	
}

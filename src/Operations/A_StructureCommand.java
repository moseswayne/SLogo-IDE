package Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.BackEndData;
import View.viewUtils.FrontEndData;
import tree.CommandNode;
import tree.ExpressionNode;
import utils.ParameterObject;

public abstract class A_StructureCommand implements CommandOperation {

	private List<ExpressionNode> instructionList;
	private BackEndData myData;

	@Override
	public void execute(ParameterObject params, BackEndData data) {
		myData = data;
		unStackList(params.getInstructions());
		modifyInstructionStack(params);
	}
	
	public abstract void modifyInstructionStack(ParameterObject params);
	
	private void unStackList(Stack<ExpressionNode> inStack) {
		instructionList = new ArrayList<ExpressionNode>();
		while(!inStack.isEmpty()) {
			instructionList.add(inStack.pop());
		}
	}
	
	public void addListStack() {
		for (ExpressionNode instruction:instructionList) {
			myData.setInstructions(instruction.cloneNode());
		}
	}
	
}

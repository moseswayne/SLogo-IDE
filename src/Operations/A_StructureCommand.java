package Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.BackEndData;
import View.FrontEndData;
import tree.CommandNode;
import utils.ParameterObject;

public abstract class A_StructureCommand implements CommandOperation {

	private List<CommandNode> instructionList;
	private BackEndData myData;

	@Override
	public void execute(ParameterObject params, BackEndData data) {
		myData = data;
		unStackList(params.getInstructions());
		modifyInstructionStack(params);
	}
	
	public abstract void modifyInstructionStack(ParameterObject params);
	
	private void unStackList(Stack<CommandNode> inStack) {
		instructionList = new ArrayList<CommandNode>();
		while(!inStack.isEmpty()) {
			instructionList.add(inStack.pop());
		}
	}
	
	public void addListStack() {
		for (CommandNode instruction:instructionList) {
			myData.setInstructions(instruction.clone());
		}
	}
	
}

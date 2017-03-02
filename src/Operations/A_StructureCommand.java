package Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_StructureCommand implements CommandOperation {

	private List<String> instructionList;

	@Override
	public void execute(ParameterObject params, FrontEndData data) {
		// TODO Auto-generated method stub
		//return null;
	}
	
	private void unStackList(Stack<String> inStack) {
		instructionList = new ArrayList<String>();
		while(!inStack.isEmpty()) {
			instructionList.add(inStack.pop());
		}
	}
	
	public void addListStack(Stack<String> inStack) {
		for (String instruction:instructionList) {
			inStack.push(instruction);
		}
	}
}

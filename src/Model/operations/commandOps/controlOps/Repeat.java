package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class Repeat extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for(int i = 0; i < params.next(); i++) {
			addListStack();
		}
		
	}

}

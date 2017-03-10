package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class For extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for(int i = params.next().intValue(); i < params.next(); i+=params.next()) {
			addListStack();
		}
		
	}

}

package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class If extends A_StructureCommand {

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		if (params.next().intValue() != 0) {
			addListStack();
		}

	}

}

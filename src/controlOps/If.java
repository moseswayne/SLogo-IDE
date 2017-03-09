package controlOps;

import Operations.A_StructureCommand;
import utils.ParameterObject;

public class If extends A_StructureCommand {

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		if (params.getDoubleAt(0).intValue() != 0) {
			addListStack();
		}

	}

}

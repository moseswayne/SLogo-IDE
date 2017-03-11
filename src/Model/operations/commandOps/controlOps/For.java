package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class For extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		String var = params.nextRaw();
		int start = params.next().intValue();
		int end = params.next().intValue();
		int increment = params.next().intValue();
		for(int i = start; i < end; i+=increment) {
			addListStack();
		}
		
	}

}

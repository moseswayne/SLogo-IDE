package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class DoTimes extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		int start = params.next().intValue();
		int end = params.next().intValue();
		for(int i = start; i < end; i++) {
			addListStack();
		}
		
	}
	
}

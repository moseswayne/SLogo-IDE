package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class DoTimes extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		String var = params.nextRaw();
		double end = params.next();
		for(double i = 0; i < end; i++) {
			addNodeStack(initializeIncrementVariable(var, i, params));
			addListStack();
		}
		
	}
	
}

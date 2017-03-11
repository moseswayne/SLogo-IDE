package Model.operations.commandOps.controlOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_StructureCommand;

public class For extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		String var = params.nextRaw();
		double start = params.next();
		double end = params.next();
		double increment = params.next();
		for(double i = start; i < end; i+=increment) {
			addNodeStack(initializeIncrementVariable(var, i, params));
			addListStack();
		}
		
	}

}

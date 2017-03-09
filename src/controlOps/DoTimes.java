package controlOps;

import Operations.A_StructureCommand;
import utils.ParameterObject;

public class DoTimes extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for(int i = params.getDoubleAt(0).intValue(); i < params.getDoubleAt(1); i++) {
			addListStack();
		}
		
	}
	
}

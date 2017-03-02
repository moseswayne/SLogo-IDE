package controlOps;

import Operations.A_StructureCommand;
import utils.ParameterObject;

public class Repeat extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for(int i = 0; i < params.getDoubleAt(0); i++) {
			addListStack(params.getInstructions());
		}
		
	}

}

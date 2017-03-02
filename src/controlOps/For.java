package controlOps;

import Operations.A_StructureCommand;
import utils.ParameterObject;

public class For extends A_StructureCommand{

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for(int i = params.getDoubleAt(1).intValue(); i < params.getDoubleAt(2); i+=params.getDoubleAt(3)) {
			addListStack(params.getInstructions());
		}
		
	}

}

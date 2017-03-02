package booleanOps;

import Operations.A_BooleanOperation;
import utils.ParameterObject;

public class Not extends A_BooleanOperation{

	@Override
	public boolean evaluate(ParameterObject params) {
		return (params.getDoubleAt(0) == 0);
	}
	
}

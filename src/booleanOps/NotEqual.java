package booleanOps;

import Operations.A_BooleanOperation;
import utils.ParameterObject;

public class NotEqual extends A_BooleanOperation{

	@Override
	public boolean evaluate(ParameterObject params) {
		return (params.getDoubleAt(0) != params.getDoubleAt(1));
	}
	
}

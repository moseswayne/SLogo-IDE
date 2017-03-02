package booleanOps;

import Operations.A_BooleanOperation;
import utils.ParameterObject;

public class Or extends A_BooleanOperation{

	@Override
	public boolean evaluate(ParameterObject params) {
		return (params.getDoubleAt(0)!=0 || params.getDoubleAt(1)!=0);
	}

}

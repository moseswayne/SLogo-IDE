package booleanOps;

import Operations.A_BooleanOperation;
import utils.ParameterObject;

public class And extends A_BooleanOperation {

	@Override
	public boolean evaluate(ParameterObject params) {
		return (params.getElement(0)!=0 && params.getElement(1)!=0);
	}

}

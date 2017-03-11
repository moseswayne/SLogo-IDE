package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_BooleanOperation;

public class Or extends A_BooleanOperation{

	@Override
	protected boolean evaluate(ParameterObject params) {
		return (params.next()!=0 || params.next()!=0);
	}

}

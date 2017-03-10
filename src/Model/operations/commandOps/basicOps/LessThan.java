package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_BooleanOperation;

public class LessThan extends A_BooleanOperation{

	@Override
	protected boolean evaluate(ParameterObject params) {
		return (params.next() < params.next());
	}
	
}

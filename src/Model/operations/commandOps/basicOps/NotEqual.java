package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_BooleanOperation;

public class NotEqual extends A_BooleanOperation{

	@Override
	protected boolean evaluate(ParameterObject params) {
		return (params.next() != params.next());
	}
	
}

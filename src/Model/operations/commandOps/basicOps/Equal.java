package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_BooleanOperation;

public class Equal extends A_BooleanOperation{

	@Override
	protected boolean evaluate(ParameterObject params) {
		return (params.next().intValue()==params.next().intValue());
	}

}

package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_MathOperation;

public class MakeVariable extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		String setKey = params.nextRaw();
		Double setVal = params.next();
		params.alterVar(setKey, setVal);
		return setVal;
	}

}

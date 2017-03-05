package mathOps;

import java.util.function.Function;

import Operations.A_MathOperation;
import utils.ParameterObject;

public abstract class A_One_Input extends A_MathOperation {
	protected static double solve(ParameterObject params, Function<Double, Double> operation) {
		return operation.apply(params.getDoubleAt(0));
	}

}

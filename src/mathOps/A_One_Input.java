package mathOps;

import java.util.function.Function;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * For math operations that only take in one input
 * @author Kris Elbert
 *
 */
public abstract class A_One_Input extends A_MathOperation {
	/**
	 * Applies an operation on the first double value of the parameterObject
	 * @param params
	 * @param operation
	 * @return
	 */
	protected static double solve(ParameterObject params, Function<Double, Double> operation) {
		return operation.apply(params.getDoubleAt(0));
	}

}

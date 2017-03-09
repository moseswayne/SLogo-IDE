package mathOps;

import java.util.function.BiFunction;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * For use with operations that can take in an unlimited number of inputs
 * 
 * @author Kris Elbert
 *
 */
public abstract class A_Two_Inputs extends A_MathOperation {
	/**
	 * Applies a function to inputs
	 * 
	 * @param source
	 * @param changingValue
	 * @param operation
	 * @return
	 */
	protected static double solve(ParameterObject params, BiFunction<Double, Double, Double> operation) {
		return operation.apply(params.getDoubleAt(0), params.getDoubleAt(1));
	}
}
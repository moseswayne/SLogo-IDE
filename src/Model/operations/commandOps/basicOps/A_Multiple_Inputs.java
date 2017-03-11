package Model.operations.commandOps.basicOps;

import java.util.Iterator;
import java.util.function.BiFunction;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_MathOperation;

/**
 * For use with operations that can take in an unlimited number of inputs
 * 
 * @author Kris Elbert
 *
 */
public abstract class A_Multiple_Inputs extends A_MathOperation {
	/**
	 * Applies a function to inputs
	 * 
	 * @param source
	 * @param changingValue
	 * @param operation
	 * @return
	 */
	protected double solve(ParameterObject params,
			BiFunction<Double, Double, Double> operation) {
		//Iterator<Double> sourceIter = source.iterator();
		Double instance;
		Double changingValue = params.next();
		while(params.hasNext()) {
			instance = params.next();
			changingValue = operation.apply(changingValue, instance);
		}
		return changingValue;
	}
}
package mathOps;

import java.util.Iterator;
import java.util.function.BiFunction;

import Operations.A_MathOperation;
import utils.ParameterObject;

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
	protected double solve(Iterable<Double> source,
			BiFunction<Double, Double, Double> operation) {
		Iterator<Double> sourceIter = source.iterator();
		Double instance;
		Double changingValue = sourceIter.next();
		while(sourceIter.hasNext()) {
			instance = sourceIter.next();
			changingValue = operation.apply(changingValue, instance);
		}
		return changingValue;
	}
}
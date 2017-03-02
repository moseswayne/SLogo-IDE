package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the first input raised to the power of the second
 * 
 * @author Elbert
 *
 */
public class RaiseToExponent extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.pow(params.getDoubleAt(0), params.getDoubleAt(1));
	}
}

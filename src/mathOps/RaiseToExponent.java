package mathOps;

import utils.ParameterObject;

/**
 * Retuns the first input raised to the power of the second
 * 
 * @author Elbert
 *
 */
public class RaiseToExponent extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		double initialValue = params.getDoubleAt(0);
		return solve(params, initialValue, (changingValue, instance) -> (Math.pow(changingValue, instance)));
	}
}

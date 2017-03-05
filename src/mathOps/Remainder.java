package mathOps;

import utils.ParameterObject;

/**
 * Divides the first value from the second and return the remainder
 * 
 * @author Elbert
 *
 */
public class Remainder extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		double initialValue = params.getDoubleAt(0);
		return solve(params, initialValue, (changingValue, instance) -> (changingValue % instance));
	}

}

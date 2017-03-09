package mathOps;

import utils.ParameterObject;

/**
 * Divides the first value from the second and return the remainder
 * 
 * @author Kris Elbert
 *
 */
public class Remainder extends A_Two_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (changingValue % instance));
	}

}

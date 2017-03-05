package mathOps;

import utils.ParameterObject;

/**
 * Subtracts the second value from the first
 * 
 * @author Kris Elbert
 *
 */
public class Subtract extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (changingValue - instance));
	}
}

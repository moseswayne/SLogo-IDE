package mathOps;

import utils.ParameterObject;

/**
 * Divides the first value by the second
 * 
 * @author Elbert
 *
 */
public class Divide extends A_Multiple_Inputs {
	private double initialValue = 1;

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, initialValue, (changingValue, instance) -> (changingValue / instance));
	}
}

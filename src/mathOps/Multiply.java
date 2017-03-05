package mathOps;

import utils.ParameterObject;

/**
 * Multiplies two values with each other
 * 
 * @author Elbert
 *
 */
public class Multiply extends A_Multiple_Inputs {
	private double initialValue = 1;

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, initialValue, (changingValue, instance) -> (changingValue * instance));
	}

}

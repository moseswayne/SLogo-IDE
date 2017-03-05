package mathOps;

import utils.ParameterObject;
/**
 * Subtracts the second value from the first
 * @author Elbert
 *
 */
public class Subtract extends A_Multiple_Inputs{
	private double initialValue = 0;

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, initialValue, (changingValue, instance) -> (changingValue - instance));
	}
}

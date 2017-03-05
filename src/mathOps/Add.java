package mathOps;

import utils.ParameterObject;
/**
 * Adds two values together
 * @author Elbert
 *
 */
public class Add extends A_Multiple_Inputs{
	private double initialValue = 0;

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, initialValue, (changingValue, instance) -> (changingValue + instance));
	}

}

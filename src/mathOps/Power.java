package mathOps;

import utils.ParameterObject;

/**
 * Retuns the first input raised to the power of the second
 * 
 * @author Elbert
 *
 */
public class Power extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (Math.pow(changingValue, instance)));
	}
}

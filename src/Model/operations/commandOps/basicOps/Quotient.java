package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;

/**
 * Divides the first value by the second
 * 
 * @author Kris Elbert
 *
 */
public class Quotient extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (changingValue / instance));
	}
}

package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;

/**
 * Adds two values together
 * 
 * @author Kris Elbert
 *
 */
public class Sum extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (changingValue + instance));
	}

}

package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;

/**
 * Retuns the sine of the input
 * 
 * @author Elbert
 *
 */
public class Sine extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.sin(value));
	}
}

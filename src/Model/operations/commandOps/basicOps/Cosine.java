package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;

/**
 * Retuns the cosine of the input
 * 
 * @author Kris Elbert
 *
 */
public class Cosine extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.cos(value));
	}
}
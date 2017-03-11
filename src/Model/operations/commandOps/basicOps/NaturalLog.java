package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;

/**
 * Retuns the natural log of the input
 * 
 * @author Elbert
 *
 */
public class NaturalLog extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.log(value));
	}
}

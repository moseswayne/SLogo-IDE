package mathOps;

import utils.ParameterObject;

/**
 * Retuns the natural log of the input
 * 
 * @author Elbert
 *
 */
public class Logarithm extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.log(value));
	}
}

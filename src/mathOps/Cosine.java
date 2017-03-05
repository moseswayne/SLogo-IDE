package mathOps;

import utils.ParameterObject;

/**
 * Retuns the cosine of the input
 * 
 * @author Elbert
 *
 */
public class Cosine extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.cos(value));
	}
}
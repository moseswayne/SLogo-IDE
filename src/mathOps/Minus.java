package mathOps;

import utils.ParameterObject;

/**
 * Makes the input negative
 * 
 * @author Kris Elbert
 *
 */
public class Minus extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> -1 * Math.abs(value));
	}
}

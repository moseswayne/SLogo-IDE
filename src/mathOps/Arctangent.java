package mathOps;

import utils.ParameterObject;

/**
 * Retuns the arctangent in degrees of the tangent input
 * 
 * @author Kris Elbert
 *
 */
public class Arctangent extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.atan(value));
	}
}
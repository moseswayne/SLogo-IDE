package mathOps;

import utils.ParameterObject;

/**
 * Multiplies two values with each other
 * 
 * @author Kris Elbert
 *
 */
public class Product extends A_Multiple_Inputs {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (changingValue, instance) -> (changingValue * instance));
	}

}

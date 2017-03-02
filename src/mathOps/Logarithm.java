package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the natural log of the input
 * 
 * @author Elbert
 *
 */
public class Logarithm extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.log(params.getDoubleAt(0));
	}
}

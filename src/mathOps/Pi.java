package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns Pi to fifteen digits after the decimal place
 * 
 * @author Elbert
 *
 */
public class Pi extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.PI;
	}
}

package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the sine of the input
 * 
 * @author Elbert
 *
 */
public class Sine extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.sin(Math.toRadians(params.getDoubleAt(0)));
	}
}

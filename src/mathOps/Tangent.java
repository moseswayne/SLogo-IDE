package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the tangent of the input
 * 
 * @author Elbert
 *
 */
public class Tangent extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.tan(Math.toRadians(params.getDoubleAt(0)));
	}
}

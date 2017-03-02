package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the arctangent in degrees of the tangent input
 * 
 * @author Elbert
 *
 */
public class Arctangent extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.toDegrees((Math.atan(params.getDoubleAt(0))));
	}
}
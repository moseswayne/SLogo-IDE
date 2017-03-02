package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns the cosine of the input
 * 
 * @author Elbert
 *
 */
public class Cosine extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.cos(Math.toRadians(params.getDoubleAt(0)));
	}
}
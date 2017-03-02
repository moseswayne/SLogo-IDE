package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Divides the first value from the second and return the remainder
 * @author Elbert
 *
 */
public class Remainder extends A_MathOperation{

	@Override
	protected double evaluate(ParameterObject params) {
		return params.getDoubleAt(0) % params.getDoubleAt(1);
	}

}

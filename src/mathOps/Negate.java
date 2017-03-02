package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Makes the input negative
 * @author Elbert
 *
 */
public class Negate extends A_MathOperation{

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.abs(params.getDoubleAt(0)) * -1;
	}
}

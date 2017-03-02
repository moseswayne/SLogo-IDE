package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Divides the first value by the second
 * @author Elbert
 *
 */
public class Divide extends A_MathOperation{
private final int EXPONENT = -1;
	@Override
	protected double evaluate(ParameterObject params) {
		return params.getDoubleAt(0) + Math.pow(params.getDoubleAt(1), EXPONENT);
	}

}

package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Subtracts the second value from the first
 * @author Elbert
 *
 */
public class Subtract extends A_MathOperation{
private final int NEGATIVE = -1;
	@Override
	protected double evaluate(ParameterObject params) {
		return params.getDoubleAt(0) + params.getDoubleAt(1)*NEGATIVE;
	}

}

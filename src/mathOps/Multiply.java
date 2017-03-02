package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Multiplies two values with each other
 * @author Elbert
 *
 */
public class Multiply extends A_MathOperation{
	@Override
	protected double evaluate(ParameterObject params) {
		return params.getDoubleAt(0) * params.getDoubleAt(1);
	}

}

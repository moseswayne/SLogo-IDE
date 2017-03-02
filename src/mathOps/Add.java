package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Adds two values together
 * @author Elbert
 *
 */
public class Add extends A_MathOperation{

	@Override
	protected double evaluate(ParameterObject params) {
		return params.getDoubleAt(0) + params.getDoubleAt(1);
	}

}

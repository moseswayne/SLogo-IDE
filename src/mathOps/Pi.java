package mathOps;

import Operations.A_MathOperation;
import utils.ParameterObject;

/**
 * Retuns Pi
 * 
 * @author Elbert
 *
 */
public class Pi extends A_MathOperation {

	@Override
	protected double evaluate(ParameterObject params) {
		return Math.PI;
	}
}

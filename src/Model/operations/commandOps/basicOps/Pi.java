package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_MathOperation;

/**
 * Retuns Pi to fifteen digits after the decimal place
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

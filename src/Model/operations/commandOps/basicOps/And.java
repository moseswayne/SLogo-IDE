package Model.operations.commandOps.basicOps;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_BooleanOperation;

/**
 * Boolean operation that implements "AND" functionality
 * Returns boolean evaluation of whether two statements are both nonzero
 * 
 * @author Moses Wayne
 *
 */
public class And extends A_BooleanOperation {

	@Override
	protected boolean evaluate(ParameterObject params) {
		return (params.next()!=0 && params.next()!=0);
	}

}

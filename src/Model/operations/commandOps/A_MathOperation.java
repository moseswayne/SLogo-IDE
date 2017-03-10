package Model.operations.commandOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;
import View.viewUtils.FrontEndData;
/**
 * Does basic math operations on inputs
 * @author Kris Elbert
 *
 */
public abstract class A_MathOperation implements CommandOperation {
	public void execute(ParameterObject params, BackEndData data){
		data.setValue(evaluate(params));
	}
	protected abstract double evaluate(ParameterObject params);
}

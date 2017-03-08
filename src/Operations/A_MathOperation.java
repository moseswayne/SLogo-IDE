package Operations;

import Model.BackEndData;
import View.FrontEndData;
import utils.ParameterObject;
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

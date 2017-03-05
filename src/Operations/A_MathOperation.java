package Operations;

import View.FrontEndData;
import utils.ParameterObject;
/**
 * Does basic math operations on inputs
 * @author Kris Elbert
 *
 */
public abstract class A_MathOperation implements CommandOperation {
	public void execute(ParameterObject params, FrontEndData data){
		data.setPrintData(evaluate(params));
	}
	protected abstract double evaluate(ParameterObject params);
}

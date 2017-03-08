package mathOps;
import utils.ParameterObject;

/**
 * Retuns the tangent of the input
 * 
 * @author Elbert
 *
 */
public class Tangent extends A_One_Input {

	@Override
	protected double evaluate(ParameterObject params) {
		return solve(params, (value) -> Math.tan(value));
	}
}

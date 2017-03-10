
package turtleOps;

import Model.TurtleModel;
import utils.ParameterObject;

/**
 * Makes the turtle invisible
 * 
 * @author Kris Elbert
 *
 */
public class HideTurtle extends A_ToggleVisibility {
	private final boolean VISIBLE = false;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		return updateTurtleState(params, (turtle) -> (turtle.setTurtleShowing(VISIBLE)));
	}

}

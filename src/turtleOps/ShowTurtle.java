
package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

/**
 * Makes the turtle visible
 * 
 * @author Elbert
 *
 */
public class ShowTurtle extends A_ToggleVisibility {
	private final boolean VISIBLE = true;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		return updateTurtleState(params, (turtle) -> (turtle.setTurtleShowing(VISIBLE)));
	}

}

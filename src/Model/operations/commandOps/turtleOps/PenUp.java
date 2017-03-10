package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

/**
 * Puts the pen up so there is no trail behind the turtle
 * 
 * @author Elbert
 *
 */
public class PenUp extends A_ToggleVisibility {
	private final boolean VISIBLE = false;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		return updateTurtleState(params, (turtle) -> (turtle.setPenShowing(VISIBLE)));
	}
}

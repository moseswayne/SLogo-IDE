package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

/**
 * Puts the pen down to leave a trail behind the turtle
 * 
 * @author Elbert
 *
 */
public class PenDown extends A_ToggleVisibility {
	private final boolean VISIBLE = true;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		return updateTurtleState(params, (turtle) -> (turtle.setPenShowing(VISIBLE)));
	}

}

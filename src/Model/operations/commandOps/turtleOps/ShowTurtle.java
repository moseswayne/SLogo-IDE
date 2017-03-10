
package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

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

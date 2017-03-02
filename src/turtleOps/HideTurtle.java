
package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

/**
 * Makes the turtle invisible
 * 
 * @author Elbert
 *
 */
public class HideTurtle extends A_TurtleCommand {
	private final boolean VISIBLE = false;
	private final int RETURN = 0;
	private TurtleModel myTurtle;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		myTurtle.setTurtleShowing(VISIBLE);
		return myTurtle;
	}

	/**
	 * Returns 1 if the pen is now down
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		return RETURN;
	}
}

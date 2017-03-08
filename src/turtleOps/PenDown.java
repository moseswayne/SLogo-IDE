package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

/**
 * Puts the pen down to leave a trail behind the turtle
 * 
 * @author Elbert
 *
 */
public class PenDown extends A_TurtleCommand {
	private final boolean DOWN = true;
	private final int RETURN = 1;
	private TurtleModel myTurtle;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		myTurtle.setPenShowing(DOWN);
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

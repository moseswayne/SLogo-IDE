package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

/**
 * Puts the pen up so there is no trail behind the turtle
 * 
 * @author Elbert
 *
 */
public class PenUp extends A_TurtleCommand {
	private final boolean DOWN = false;
	private final int RETURN = 0;
	private TurtleModel myTurtle;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		myTurtle.setPenShowing(DOWN);
		return myTurtle;
	}

	/**
	 * Returns 0 if the pen is now up
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		return RETURN;
	}
}

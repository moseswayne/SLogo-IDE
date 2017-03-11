package Model.operations.commandOps.turtleOps;

/**
 * Corresponds to counterclockwise
 * 
 * @author Kris Elbert
 *
 */

public class Left extends A_TurnTurtle {

	@Override
	protected int setDirection() {
		return -1;
	}
}

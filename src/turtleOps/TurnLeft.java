package turtleOps;

/**
 * Corresponds to counterclockwise
 * 
 * @author Kris Elbert
 *
 */

public class TurnLeft extends A_TurnTurtle {

	@Override
	protected int setDirection() {
		return -1;
	}
}

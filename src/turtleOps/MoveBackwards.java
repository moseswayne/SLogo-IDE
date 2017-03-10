package turtleOps;
/**
 * Moves the turtle backwards by turning around
 * @author Kris Elbert
 *
 */
public class MoveBackwards extends A_MoveTurtle {
	@Override
	protected int getOffset() {
		return -180;
	}
}
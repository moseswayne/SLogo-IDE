package Model.operations.commandOps.turtleOps;
/**
 * Moves the turtle backwards by turning around
 * @author Kris Elbert
 *
 */
public class Backward extends A_MoveTurtle {
	@Override
	protected int getOffset() {
		return -180;
	}
}
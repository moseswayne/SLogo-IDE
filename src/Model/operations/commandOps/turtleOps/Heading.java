package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.operations.commandOps.A_TurtleQuery;

/**
 * Returns the heading of the turtle
 * 
 * @author Kris Elbert
 *
 */
public class Heading extends A_TurtleQuery {

	@Override
	protected double returnQuery(TurtleModel turtle) {
		return turtle.getHeading();
	}
}

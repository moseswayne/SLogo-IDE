package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.operations.commandOps.A_TurtleQuery;
/**
 * Returns the Y coordinate in relation to the center of the screen
 * @author Elbert
 *
 */
public class YCoordinate extends A_TurtleQuery{

	@Override
	protected double returnQuery(TurtleModel turtle) {
		return turtle.getY();
	}
	

}

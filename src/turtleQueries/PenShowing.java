package turtleQueries;

import Model.TurtleModel;
import Operations.A_TurtleQuery;
/**
 * Returns 1 if the pen is down or showing or 0 if it is up
 * @author Elbert
 *
 */
public class PenShowing extends A_TurtleQuery{

	@Override
	protected double returnQuery(TurtleModel turtle) {
		if(turtle.getPenShowing()){
			return 1;
		}
		return 0;
	}
}

package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.operations.commandOps.A_TurtleQuery;

/**
 * Returns 1 if the pen is down or showing or 0 if it is up
 * 
 * @author Elbert
 *
 */
public class IsPenDown extends A_TurtleQuery implements I_CheckTurtleConditions {

	@Override
	protected double returnQuery(TurtleModel turtle) {
		if (check(turtle)) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean check(TurtleModel turtle) {
		return turtle.getPenShowing();
	}

}

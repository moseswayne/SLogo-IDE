package turtleQueries;

import Model.TurtleModel;
import Operations.A_TurtleQuery;

/**
 * Returns 1 if the turtle is visible or showing or 0 if it is hidden
 * 
 * @author Kris Elbert
 *
 */
public class IsShowing extends A_TurtleQuery implements I_CheckTurtleConditions {

	@Override
	protected double returnQuery(TurtleModel turtle) {
		if (check(turtle)) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean check(TurtleModel turtle) {
		return turtle.getTurtleShowing();
	}
}

package turtleQueries;

import Model.TurtleModel;
import Operations.A_TurtleQuery;
/**
 * Returns 1 if the turtle is visible or showing or 0 if it is hidden
 * @author Elbert
 *
 */
public class IsShowing extends A_TurtleQuery{

	@Override
	protected double returnQuery(TurtleModel turtle) {
		if(turtle.getTurtleShowing()){
			return 1;
		}
		return 0;
	}
}

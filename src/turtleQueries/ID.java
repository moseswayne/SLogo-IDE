package turtleQueries;

import Model.TurtleModel;
import Operations.A_TurtleQuery;

public class ID extends A_TurtleQuery{

	@Override
	protected double returnQuery(TurtleModel turtle) {
		return turtle.getID();
	}

}

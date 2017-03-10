package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.operations.commandOps.A_TurtleQuery;

public class ID extends A_TurtleQuery{

	@Override
	protected double returnQuery(TurtleModel turtle) {
		return turtle.getID();
	}

}

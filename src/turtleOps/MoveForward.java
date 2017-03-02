package turtleOps;

import Operations.A_TurtleCommand;
import utils.ParameterObject;
import utils.TurtleParameters;

public class MoveForward extends A_TurtleCommand {
	private static int DIRECTION = 1;
	private double distance;

	@Override
	public TurtleParameters makeTurtleParameters(ParameterObject params) {
		myTurtle = params.getTurtle();
		distance = returnValue(params);
		return new TurtleParameters(myTurtle.newXCoordinate(distance, DIRECTION),
				myTurtle.newYCoordinate(distance, DIRECTION), myTurtle.getHeading(), myTurtle.getPenShowing());
	}

	@Override
	public double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}

}

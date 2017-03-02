package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class TurnRight extends A_TurtleCommand {
	private double degrees;
	private static int DIRECTION = 1;
	private static int FULL_CIRCLE = 360;
	private TurtleModel myTurtle;

	// corresponds to counter-clockwise
	@Override
	public TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		degrees = returnValue(params);
		myTurtle.setHeading(newHeading(myTurtle, degrees));
		return myTurtle;
	}

	@Override
	public double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}

	private double newHeading(TurtleModel turtle, double degreeChange) {
		double newHeading = turtle.getHeading() + degreeChange * DIRECTION;
		int temp = 1;
		while (temp != 0) {
			if (newHeading > FULL_CIRCLE) {
				temp = 1;
			} else if (newHeading < 0) {
				temp = -1;
			} else {
				temp = 0;
			}
			newHeading += FULL_CIRCLE * temp;
		}
		return newHeading;
	}
}


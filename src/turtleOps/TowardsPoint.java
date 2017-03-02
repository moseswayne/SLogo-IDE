package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class TowardsPoint extends A_TurtleCommand {
	private TurtleModel myTurtle;
	private double previousHeading;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		previousHeading = myTurtle.getHeading();
		myTurtle.setHeading(getAbsoluteHeading(params.getDoubleAt(0),params.getDoubleAt(1), myTurtle.getX(), myTurtle.getY()));
		return myTurtle;
	}

	/**
	 * Returns the number of degrees moved
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		double value;
		try {
			// if updateTurtle called before returnValue
			value = myTurtle.getHeading() - previousHeading;
		} catch (NullPointerException e) {
			myTurtle = params.getTurtle();
			value = getAbsoluteHeading(params.getDoubleAt(0),params.getDoubleAt(1), myTurtle.getX(), myTurtle.getY()) - myTurtle.getHeading();
		}
		return value;
	}

	private double getAbsoluteHeading(double newX, double newY, double currentX, double currentY) {
		return Math.toDegrees(
				Math.atan(Math.abs(newY - currentY) / Math.abs(newX - currentX)));
	}
}

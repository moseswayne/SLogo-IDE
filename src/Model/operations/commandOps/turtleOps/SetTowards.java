package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

public class SetTowards extends A_TurtleCommand {
	private TurtleModel myTurtle;
	private double previousHeading;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		previousHeading = myTurtle.getHeading();
		myTurtle.setHeading(getAbsoluteHeading(params.next(), params.next(), myTurtle.getX(), myTurtle.getY()));
		return myTurtle;
	}

	/**
	 * Returns the number of degrees moved
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		double difference;
		try {
			// if updateTurtle called before returnValue
			difference = myTurtle.getHeading() - previousHeading;
		} catch (NullPointerException e) {
			updateTurtle(params);S
			difference = this.returnValue(params);
		}
		return difference;
	}

	private double getAbsoluteHeading(double newX, double newY, double currentX, double currentY) {
		return Math.toDegrees(Math.atan(Math.abs(newY - currentY) / Math.abs(newX - currentX)));
	}
}

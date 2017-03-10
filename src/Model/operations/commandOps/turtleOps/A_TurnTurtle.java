package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

/**
 * Rotates Turtle in a specified direction
 * 
 * @author Kris Elbert
 *
 */
// TODO may not want to extend manipulate turtle
public abstract class A_TurnTurtle extends A_TurtleCommand {
	private final int FULL_CIRCLE = 360;
	private double myDegrees;

	@Override
	public TurtleModel updateTurtle(ParameterObject params) {
		TurtleModel myTurtle = params.getTurtle();
		myDegrees = returnValue(params);
		myTurtle.setHeading(newHeading(myTurtle, myDegrees));
		return myTurtle;
	}

	private double newHeading(TurtleModel turtle, double degreeChange) {
		double newHeading = turtle.getHeading() + degreeChange * setDirection();
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

	/**
	 * -1 corresponds to counter-clockwise
	 * 
	 * @return
	 */
	protected abstract int setDirection();

	protected double returnValue(ParameterObject params) {
		try {
			return myDegrees;
		} catch (NullPointerException e) {
			updateTurtle(params);
			return returnValue(params);
		}
	}
}

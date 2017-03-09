package turtleOps;

import Model.TurtleModel;
import utils.ParameterObject;

/**
 * Rotates Turtle in a specified direction
 * 
 * @author Kris Elbert
 *
 */
//TODO may not want to extend manipulate turtle
public abstract class A_TurnTurtle extends A_ManipulateTurtle {
	private static int FULL_CIRCLE = 360;

	@Override
	public TurtleModel updateTurtle(ParameterObject params) {
		TurtleModel myTurtle = params.getTurtle();
		Double degrees = returnValue(params);
		myTurtle.setHeading(newHeading(myTurtle, degrees));
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
}

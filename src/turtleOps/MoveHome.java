package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class MoveHome extends A_ManipulateTurtle {
	private final double HOME_X = 0;
	private final double HOME_Y = 0;
	private double prevX;
	private double prevY;

	/**
	 * Moves turtle to an absolute point
	 * 
	 * @author Elbert
	 */

	double updateX(Double oldX, ParameterObject params) {
		prevX = params.getTurtle().getX();
		return HOME_X;
	}

	double updateY(Double oldY, ParameterObject params) {
		prevY = params.getTurtle().getY();
		return HOME_Y;

	}

	/**
	 * Returns the straight-line distance moved between points in the update
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		// if updateTurtle called before
		TurtleModel myTurtle = params.getTurtle();
		try {
			return getDistance(myTurtle.getX(), myTurtle.getY(), prevX, prevY);
		} catch (NullPointerException e) {
			myTurtle = params.getTurtle();
			return getDistance(HOME_X, HOME_Y, myTurtle.getX(), myTurtle.getY());
		}
	}

	/**
	 * Gets distance from current point to new input point
	 * 
	 * @param x
	 *            is the new x coordinate
	 * @param y
	 *            is the new y coordinate
	 * @return a double of the straight line distance
	 */
	private double getDistance(double newX, double newY, double currentX, double currentY) {
		return Math.sqrt((newX - currentX) * (newX - currentX) + (newY - currentY) * (newY - currentY));
	}
}

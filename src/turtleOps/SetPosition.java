package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class SetPosition extends A_ManipulateTurtle {
	private double prevX;
	private double prevY;
	/**
	 * Moves turtle to an absolute point
	 * 
	 * @author Kris Elbert
	 */
	private TurtleModel myTurtle;

	double updateX(Double oldX, ParameterObject params) {
		prevX = params.getTurtle().getX();
		return params.getDoubleAt(0);
	}

	double updateY(Double oldY, ParameterObject params) {
		prevY = params.getTurtle().getY();
		return params.getDoubleAt(1);

	}

	/**
	 * Returns the straight-line distance moved between points in the update
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		// if updateTurtle called before
		try {
			return getDistance(myTurtle.getX(), myTurtle.getY(), prevX, prevY);
		} catch (NullPointerException e) {
			myTurtle = params.getTurtle();
			return getDistance(params.getDoubleAt(0), params.getDoubleAt(1), myTurtle.getX(), myTurtle.getY());
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

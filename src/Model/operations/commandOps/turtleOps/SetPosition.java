package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;

public class SetPosition extends A_MoveTurtle {
	private double prevX;
	private double prevY;
	private double[] newCoordinates;
	/**
	 * Moves turtle to an absolute point
	 * 
	 * @author Kris Elbert
	 */
	private TurtleModel myTurtle;

	@Override
	protected double[] updateCoordinates(Double oldX, Double oldY, ParameterObject params) {
		prevX = oldX;
		prevY = oldY;
		newCoordinates[0] = params.next();
		newCoordinates[1] = params.next();
		return newCoordinates;

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
			updateCoordinates(params.getTurtle().getX(), params.getTurtle().getY(), params);
			return returnValue(params);
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

	@Override
	protected int getOffset() {
		return 0;
	}

}

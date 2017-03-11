package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

public class Home extends A_MoveTurtle {
	private final double[] HOME_COORDINATES = { 0, 0 };
	private double prevX;
	private double prevY;

	/**
	 * Moves turtle to an absolute point
	 * 
	 * @author Elbert
	 */
	@Override
	double[] updateCoordinates(Double oldX, Double oldY, ParameterObject params) {
		prevX = oldX;
		prevY = oldY;
		return HOME_COORDINATES;
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
			return getDistance(HOME_COORDINATES[0], HOME_COORDINATES[1], myTurtle.getX(), myTurtle.getY());
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

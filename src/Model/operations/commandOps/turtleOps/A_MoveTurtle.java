package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;

/**
 * to allow the turtle to move backwards and forwards, or really in any
 * direction
 * 
 * @author Kris Elbert
 *
 */
public abstract class A_MoveTurtle extends A_ManipulateTurtle {

	double updateX(Double oldX, ParameterObject params) {
		return oldX + changeInX(params.getTurtle().getHeading(), params.getDoubleAt(0));
	}

	double updateY(Double oldY, ParameterObject params) {
		return oldY + changeInY(params.getTurtle().getHeading(), params.getDoubleAt(1));
	}

	abstract protected int getOffset();

	/**
	 * Gets x and y coordinates based on total distance traveled and the
	 * direction traveled in
	 * 
	 * @param distance
	 * @param direction
	 * @return double representing the new coordinate
	 */
	private double changeInX(double heading, double distance) {
		return Math.sin(Math.toRadians(heading + getOffset())) * distance;
	}

	private double changeInY(double heading, double distance) {
		return Math.cos(Math.toRadians(heading + getOffset())) * distance;
	}
}

package turtleOps;

import Model.TurtleModel;
import utils.ParameterObject;

/**
 * to allow the turtle to move backwards and forwards, or really in any
 * direction
 * 
 * @author Kris Elbert
 *
 */
public abstract class A_MoveTurtle extends A_ManipulateTurtle {
	private int OFFSET;
	private double DISTANCE;

	public TurtleModel updateTurtle(ParameterObject params) {
		TurtleModel myTurtle = params.getTurtle();
		DISTANCE = returnValue(params);
		OFFSET = setOffset();
		myTurtle.setX(myTurtle.getX() + changeInX(myTurtle.getHeading(), DISTANCE));
		myTurtle.setY(myTurtle.getY() + changeInY(myTurtle.getHeading(), DISTANCE));
		return myTurtle;
	}

	abstract protected int setOffset();

	/**
	 * Gets x and y coordinates based on total distance traveled and the
	 * direction traveled in
	 * 
	 * @param distance
	 * @param direction
	 * @return double representing the new coordinate
	 */
	private double changeInX(double heading, double distance) {
		return Math.sin(Math.toRadians(heading + OFFSET)) * distance;
	}

	private double changeInY(double heading, double distance) {
		return Math.cos(Math.toRadians(heading) + OFFSET) * distance;
	}
}

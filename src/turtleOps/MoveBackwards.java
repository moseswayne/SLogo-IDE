package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class MoveBackwards extends A_TurtleCommand {
	private static int OFFSET = -180;
	private double distance;
	private TurtleModel myTurtle;

	@Override
	public TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		distance = returnValue(params);
		myTurtle.setX(myTurtle.getX() + changeInX(myTurtle.getHeading(), distance));
		myTurtle.setY(myTurtle.getY() + changeInY(myTurtle.getHeading(), distance));
		return myTurtle;
	}

	@Override
	public double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}

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
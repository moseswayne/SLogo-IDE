package Model.operations.commandOps.turtleOps;

import java.util.function.Function;

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
	protected TurtleModel updateTurtle(ParameterObject params) {
		TurtleModel turtle = params.getTurtle();
		double[] coordinateArray = updateCoordinates(turtle.getX(), turtle.getY(), params);
		turtle.setX(coordinateArray[0]);
		turtle.setY(coordinateArray[1]);
		return turtle;
	}

	double[] updateCoordinates(Double oldX, Double oldY, ParameterObject params) {
		double changeParam = params.peekNext();
		double[] array = { (oldX + changeInX(params.getTurtle().getHeading(), changeParam)),
				(oldY - changeInY(params.getTurtle().getHeading(), changeParam)) };
		return array;
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
	private double change(double heading, double distance, Function<Double, Double> operation) {
		return operation.apply(Math.toRadians(heading + getOffset())) * distance;
	}

	private double changeInX(double heading, double distance) {
		return change(heading, distance, (value) -> Math.sin(value));
	}

	private double changeInY(double heading, double distance) {
		return change(heading, distance, (value) -> Math.cos(value));
	}
}

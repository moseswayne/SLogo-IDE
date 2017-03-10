package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

public class ClearScreen extends A_TurtleCommand{
	private final double HOME_X = 0;
	private final double HOME_Y = 0;
private double prevX;
private double prevY;
/**
 * Moves turtle to an absolute point
 * 
 * @author Elbert
 */
//TODO redundant with MoveToHome method and does not communicate the need to clear the screen
private TurtleModel myTurtle;
	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		prevX = myTurtle.getX();
		prevY = myTurtle.getY();
		myTurtle.setX(HOME_X);
		myTurtle.setY(HOME_Y);
		return myTurtle;
	}
/**
 * Returns the straight-line distance moved between points in the update
 */
	@Override
	protected double returnValue(ParameterObject params) {
		//if updateTurtle called before
		try{
			return getDistance(myTurtle.getX(), myTurtle.getY(), prevX, prevY);
		} catch (NullPointerException e){
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

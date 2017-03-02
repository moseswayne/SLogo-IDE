package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

public class SetPoint extends A_TurtleCommand{
private double prevX;
private double prevY;
/**
 * Moves turtle to an absolute point
 * 
 * @author Elbert
 */
private TurtleModel myTurtle;
	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		prevX = myTurtle.getX();
		prevY = myTurtle.getY();
		myTurtle.setX(params.getDoubleAt(0));
		myTurtle.setY(params.getDoubleAt(1));
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

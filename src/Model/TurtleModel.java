package Model;

/**
 * 
 * @author Kris Elbert
 */
public class TurtleModel {
	private static double HOMEX = 0, HOMEY = 0;
	private double myX, myY, myHeading;
	private boolean isPenShowing, isTurtleShowing;

	/**
	 * Creates a new TurtleModel at the default HOME position
	 */
	public TurtleModel() {
		this(HOMEX, HOMEY);
	}

	/**
	 * Makes a turtle facing north at a specified point
	 * 
	 * @param x
	 *            is the x coordinate
	 * @param y
	 *            is the y coordinate
	 */
	public TurtleModel(double x, double y) {
		myX = x;
		myY = y;
		myHeading = 0;
		isPenShowing = true;
		isTurtleShowing = true;
	}

	/**
	 * makes a copy of another TurtlePosition
	 * 
	 * @param other
	 */
	public TurtleModel(TurtleModel other) {
		this.myX = other.getX();
		this.myY = other.getY();
		this.myHeading = other.getHeading();
	}

	/**
	 * Getters
	 */
	public double getX() {
		return myX;
	}

	public double getY() {
		return myY;
	}

	public double getHeading() {
		return myHeading;
	}

	public boolean getPenShowing() {
		return isPenShowing;
	}

	public boolean getTurtleShowing() {
		return isTurtleShowing;
	}

	/**
	 * Setters
	 */
	public double setX(double newX) {
		return myX = newX;
	}

	public double setY(double newY) {
		return myY = newY;
	}

	public double setHeading(double newHeading) {
		return myHeading = newHeading;
	}

	public boolean setTurtleShowing(boolean visibility) {
		return isTurtleShowing = visibility;
	}

	public boolean setPenShowing(boolean visibility) {
		return isPenShowing = visibility;
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
	public double getDistance(double x, double y) {
		return Math.sqrt((x - this.myX) * (x - this.myX) + (y - this.myY) * (y - this.myY));
	}

	
}

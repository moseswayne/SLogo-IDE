package Model;

/**
 * 
 * @author Kris Elbert
 */
public class TurtleModel {
	private static double HOMEX = 0, HOMEY = 0;
	private double myX, myY, myHeading;
	private boolean isPenShowing, isTurtleShowing;
	private int myID;

	/**
	 * Creates a new TurtleModel at the default HOME position
	 */
	public TurtleModel(int id) {
		this(HOMEX, HOMEY, id);
	}

	/**
	 * Makes a turtle facing north at a specified point
	 * 
	 * @param x
	 *            is the x coordinate
	 * @param y
	 *            is the y coordinate
	 */
	public TurtleModel(double x, double y, int id) {
		myX = x;
		myY = y;
		myHeading = 0;
		isPenShowing = true;
		isTurtleShowing = true;
		myID = id;
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

	// ID count starts at 1
	public int getID() {
		return myID;
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
}

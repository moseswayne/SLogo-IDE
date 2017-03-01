package Model;

public class TurtleModel {
	private double myX;
	private double myY;
	private double myHeading;
	private boolean isPenShowing;
	private boolean isTurtleShowing;

	protected TurtleModel() {
		// TurtleModel(0, 0);
	}

	protected TurtleModel(double x, double y) {
		myX = x;
		myY = y;
		myHeading = 0;
		isPenShowing = true;
		isTurtleShowing = true;
	}

	protected double getX() {
		return myX;
	}

	protected double getY() {
		return myY;
	}

	protected double getHeading() {
		return myHeading;
	}

	protected double setX(double newX) {
		return myX = newX;
	}

	protected double setY(double newY) {
		return myY = newY;
	}

	protected double setHeading(double newHeading) {
		return myHeading = newHeading;
	}

	protected boolean isPenShowing() {
		return isPenShowing;
	}

	protected boolean isTurtleShowing() {
		return isTurtleShowing;
	}

	protected boolean isTurtleShowing(boolean visibility) {
		return isTurtleShowing = visibility;
	}

	protected boolean isPenShowing(boolean visibility) {
		return isPenShowing = visibility;
	}

	protected double distance(double x, double y) {
		return Math.sqrt((x - this.myX) * (x - this.myX) + (y - this.myY) * (y - this.myY));
	}

}

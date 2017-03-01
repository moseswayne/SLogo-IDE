package utils;

/**
 * 
 * @author Yuxiang He
 *
 */
public class TurtlePosition {
	private double x;
	private double y;
	private double heading;
	
	/**
	 * 
	 * @param _x
	 * @param _y
	 * @param _heading
	 */
	public TurtlePosition(double _x, double _y, double _heading) {
		x=_x;
		y=_y;
		heading=_heading;
	}
	
	/**
	 * makes a copy of another TurtlePosition
	 * @param other
	 */
	public TurtlePosition(TurtlePosition other){
		this.x=other.getX();
		this.y=other.getY();
		this.heading=other.getHeading();
	}
	
	/**
	 * getters
	 *
	 */
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}

	public double getHeading(){
		return heading;
	}
}

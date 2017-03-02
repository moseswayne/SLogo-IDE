package utils;

/**
 * 
 * @author Yuxiang He
 *
 */
public class TurtleParameters {
	private double x;
	private double y;
	private double heading;
	private boolean isPendown;
	private boolean turtleVisible;
	
	/**
	 * 
	 * @param _x
	 * @param _y
	 * @param _heading
	 */
	public TurtleParameters(double _x, double _y, double _heading, boolean _isPendown, boolean _turtleVisible) {
		x=_x;
		y=_y;
		heading=_heading;
		isPendown=_isPendown;
		turtleVisible=_turtleVisible;
	}
	
	/**
	 * makes a copy of another TurtlePosition
	 * @param other
	 */
	public TurtleParameters(TurtleParameters other){
		this.x=other.getX();
		this.y=other.getY();
		this.heading=other.getHeading();
		this.isPendown=other.isPendown();
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
	
	public boolean isPendown(){
		return isPendown;
	}
	
	public boolean turtleVisible(){
		return turtleVisible;
	}
}
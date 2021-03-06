package utils;

import Model.TurtleModel;

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
	private int id;
	private boolean isActive;
	
	/**
	 * 
	 * @param _x
	 * @param _y
	 * @param _heading
	 */
	public TurtleParameters(int _id, double _x, double _y, double _heading, boolean _isPendown, boolean _turtleVisible, boolean _isActive) {
		id=_id;
		x=_x;
		y=_y;
		heading=_heading;
		isPendown=_isPendown;
		turtleVisible=_turtleVisible;
		isActive=_isActive;
		//TODO need to set ID
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
		this.id=other.getID();
	}
	/**
	 * Makes TurtleParamters from a backend turtle
	 * @param turtle
	 */
	public TurtleParameters(TurtleModel turtle){
		x=turtle.getX();
		y=turtle.getY();
		heading=turtle.getHeading();
		isPendown=turtle.getPenShowing();
		turtleVisible=turtle.getTurtleShowing();
		id=turtle.getID();
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
	
	public boolean isActive(){
		return isActive;
	}
	
	public int getID(){
		return id;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}

}
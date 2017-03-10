package utils;

import Model.TurtleModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * 
 * @author Yuxiang He
 *
 */
public class TurtleParameters {
	private SimpleDoubleProperty x=new SimpleDoubleProperty();
	private SimpleDoubleProperty y=new SimpleDoubleProperty();
	private SimpleDoubleProperty heading=new SimpleDoubleProperty();
	private SimpleBooleanProperty isPendown=new SimpleBooleanProperty();
	private SimpleBooleanProperty turtleVisible=new SimpleBooleanProperty();
	private int id;
	private SimpleBooleanProperty isActive=new SimpleBooleanProperty();
	
	/**
	 * 
	 * @param _x
	 * @param _y
	 * @param _heading
	 */
	public TurtleParameters(int _id, double _x, double _y, double _heading, boolean _isPendown, boolean _turtleVisible, boolean _isActive) {
		id= _id;
		x.set(_x); 
		y.set(_y);
		heading.set(_heading);
		isPendown.set(_isPendown);
		turtleVisible.set(_turtleVisible);
		isActive.set(_isActive);
		//TODO need to set ID
	}
	
	/**
	 * makes a copy of another TurtlePosition
	 * @param other
	 */
	public TurtleParameters(TurtleParameters other){
		this.id=other.getID();
		this.x=other.getXProperty();
		this.y=other.getYProperty();
		this.heading=other.getHeadingProperty();
		this.isPendown=other.getPenDownProperty();
		this.isActive=other.isActive;

	}
	/**
	 * Makes TurtleParamters from a backend turtle
	 * @param turtle
	 */
	public TurtleParameters(TurtleModel turtle){
		this(turtle.getID(), turtle.getX(), turtle.getY(), turtle.getHeading(), turtle.getPenShowing(), turtle.getTurtleShowing(), true);//does not matter if turtle is active or not
	}
	/**
	 * getters
	 *
	 */
	
	public int getID(){
		return id;
	}
	
	public SimpleDoubleProperty getXProperty(){
		return x;
	}
	
	public SimpleDoubleProperty getYProperty(){
		return y;
	}
	
	public SimpleDoubleProperty getHeadingProperty(){
		return heading;
	}
	
	public SimpleBooleanProperty getPenDownProperty(){
		return isPendown;
	}
	
	public SimpleBooleanProperty getVisibleProperty(){
		return turtleVisible;
	}
	
	public SimpleBooleanProperty getActiveProperty(){
		return isActive;
	}
	
}
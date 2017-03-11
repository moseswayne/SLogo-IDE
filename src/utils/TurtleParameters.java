package utils;

import Model.TurtleModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;



public class TurtleParameters {
	private int id;
	private SimpleDoubleProperty x=new SimpleDoubleProperty();
	private SimpleDoubleProperty y=new SimpleDoubleProperty();
	private SimpleDoubleProperty heading=new SimpleDoubleProperty();
	private SimpleBooleanProperty isPendown=new SimpleBooleanProperty();
	private SimpleBooleanProperty turtleVisible=new SimpleBooleanProperty();
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
		copy(other);
	}
	
	public void copy(TurtleParameters other){
		setX(other.getXProperty());
		setY(other.getYProperty());
		setHeading(other.getHeadingProperty());
		setIsPendown(other.getPenDownProperty());
		setIsActive(other.getActiveProperty());
		setTurtleVisible(other.getVisibleProperty());
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

	
	/**
	 * setters
	 */
	public void setX(SimpleDoubleProperty x) {
		this.x.setValue(x.getValue());;
	}

	public void setY(SimpleDoubleProperty y) {
		this.y.setValue(y.getValue());;
	}

	public void setHeading(SimpleDoubleProperty heading) {
		this.heading.setValue(heading.getValue());;
	}

	public void setIsPendown(SimpleBooleanProperty isPendown) {
		this.isPendown.setValue(isPendown.getValue());
	}

	public void setTurtleVisible(SimpleBooleanProperty turtleVisible) {
		this.turtleVisible.setValue(turtleVisible.getValue());
	}

	public void setIsActive(SimpleBooleanProperty isActive) {
		this.isActive.setValue(isActive.getValue());
	}
	
}
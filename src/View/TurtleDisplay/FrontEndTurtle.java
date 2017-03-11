package View.TurtleDisplay;

import java.util.Properties;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.PropertyUtility;
import utils.TurtleParameters;

public class FrontEndTurtle extends ImageView {
	private TurtleParameters myParams;
	
	public FrontEndTurtle(TurtleParameters _myParams, Image myImage) {
		this(_myParams);
		this.setImage(myImage);
		
	}
	
	public FrontEndTurtle(TurtleParameters _myParams) {
		Properties prop=new PropertyUtility("deafaultTurtleDisp.properties").getProperties();
		Image myImage =  new Image(getClass().getClassLoader().getResourceAsStream(prop.getProperty("deafaultTurtleImage")));
		this.setImage(myImage);
		myParams=_myParams;
	}
	
	/**
	 * Assumes the right parameter has come through
	 * @param params
	 */
	void setParams(TurtleParameters params){
		myParams=params;
	}
	
	int getID(){
		return myParams.getID();
	}
	
	@Override
	public String toString(){
		return String.format("id: %d, X: %f, Y: %f\n heading: %f, penDown: %b, visible: %b\n", myParams.getID(),
				myParams.getX(), myParams.getY(), myParams.getHeading(), myParams.isPendown(), myParams.turtleVisible());			
	}
	
}

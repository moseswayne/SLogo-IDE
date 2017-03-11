package View.TurtleDisplay;

import java.util.Properties;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
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


	int getID(){
		return myParams.getID();
	}
	
	void setParams(TurtleParameters params){
		if(params.getID()==myParams.getID()){
			myParams=params;
		} else {
			System.out.println("turtle id doesn;'t match");
		}
	}
	
	@Override
	public String toString(){
		return String.format("id: %d, X: %f, Y: %f\n heading: %f, penDown: %b, visible: %b", 
				myParams.getX(), myParams.getY(), myParams.getHeading(), myParams.isPendown(), myParams.turtleVisible());			
	}
	
}

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
		this.xProperty().bind(myParams.getXProperty());
		this.yProperty().bind(myParams.getYProperty());
		this.rotateProperty().bind(myParams.getHeadingProperty());
		this.visibleProperty().bind(myParams.getVisibleProperty());
	}


	public int getID(){
		return myParams.getID();
	}
	
	public void setParams(TurtleParameters params){
		if(params.getID()==myParams.getID()){
			myParams.setX(params.getXProperty());
			myParams.setY(params.getYProperty());
			myParams.setHeading(params.getHeadingProperty());
			myParams.setIsActive(params.getActiveProperty());
			myParams.setIsPendown(params.getPenDownProperty());
			myParams.setTurtleVisible(params.getVisibleProperty());
		} else {
			System.out.println("turtle id doesn;'t match");
		}
	}
	
}

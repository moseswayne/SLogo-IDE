package View.viewUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.TurtleParameters;

public class FrontEndTurtle extends ImageView {
	private TurtleParameters myParams;
	
	public FrontEndTurtle(TurtleParameters _myParams, Image myImage) {
		this.setImage(myImage);
		myParams=_myParams;
		this.xProperty().bind(myParams.getXProperty());
		this.yProperty().bind(myParams.getYProperty());
		this.rotateProperty().bind(myParams.getHeadingProperty());
		this.visibleProperty().bind(myParams.getVisibleProperty());
	}
	

}

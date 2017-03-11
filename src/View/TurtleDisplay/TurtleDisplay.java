package View.TurtleDisplay;

import javafx.scene.paint.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Properties;
import java.util.Queue;
import View.I_FrontEndModule;
import View.viewUtils.FrontEndData;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import utils.ColorManager;
import utils.Language;
import utils.PropertyUtility;
import utils.RawCommand;
import utils.TurtleParameters;

public class TurtleDisplay implements I_FrontEndModule {
	private Paint penColor;
	private Dimension paneSize;
	private Properties prop;
	private ColorManager myColorManager;
	private TurtleContainer turtleContainer;
	private StackPane container;
	
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDisplay(int height, int width, ColorManager _myColorManager) {
		prop=new PropertyUtility("deafaultTurtleDisp.properties").getProperties();
		myColorManager=_myColorManager;
		setPenColor(myColorManager.getColor(prop.getProperty("DEAFAULT_PEN_COLOR")));
		paneSize=new Dimension(height, width);
		turtleContainer=new TurtleContainer(width, width);
		container=new StackPane(turtleContainer.getContainerNode());
		setBackgroudColor(myColorManager.getColor(prop.getProperty("DEAFAULT_BACKGROUND_COLOR")));
		standardizeSize(container);
	}


	/**
	 * binds the size of a Region (namely container and turtleContainer in this case) 
	 * to the size of the "turtleDisplay"
	 * @param region
	 */
	private void standardizeSize(Region region) {
		region.setPrefSize(paneSize.getWidth(), paneSize.getHeight());
		region.setMaxSize(paneSize.getWidth(), paneSize.getHeight());
		region.setMinSize(paneSize.getWidth(), paneSize.getHeight());
	}


	
	/**
	 * 
	 * @param color String specifying the color, USE VALID
	 */
	public void setBackgroudColor(Color color){
		int blue=(int) (255*color.getBlue()),
				red=(int) (255*color.getRed()),
				green=(int) (255*color.getGreen());
		container.setStyle(String.format("-fx-background-color: rgb(%d, %d, %d)", red, green, blue));
	}
	
	public void setPenColor(Color color){
		penColor=color;
	}
	

	/**
	 * 
	 * API for updating visualization
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		Queue<TurtleParameters> paramQ=data.getTurtleParameters();
		turtleContainer.show(paramQ, penColor);
	}


	public void setTurtleImg(File file) {
		turtleContainer.setTurtleImg(file);
	}

    
    
	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO potentially dangerous?
	@Override
	public Node getVisualizedContent() {
		return container;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		return false;
	}

	/**
	 * Has no displayed language components
	 */
	@Override
	public void setLanguage(Language language) {
		return;
	}


}
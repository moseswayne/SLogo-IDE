package View;

import javafx.scene.paint.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Queue;

import View.viewUtils.FrontEndData;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import utils.Language;
import utils.PropertyUtility;
import utils.RawCommand;
import utils.TurtleParameters;

public class TurtleDisplay implements I_FrontEndModule {
	private Canvas lineCanvas;
	private Pane turtleContainer;
	private StackPane container;
	private ImageView turtle;
	private TurtleParameters currentParams;
	private Paint penColor;
	private Dimension paneSize;
	private Properties prop;
	private final Dimension TURTLE_SIZE=new Dimension(20, 20);
	private GUI myGUI;
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDisplay(int height, int width, GUI _myGUI) {
		myGUI=_myGUI;
		prop=new PropertyUtility("deafaultTurtleDisp.properties").getProperties();
		currentParams=new TurtleParameters(0,Double.parseDouble(prop.getProperty("DEAFAULT_X")),
				Double.parseDouble(prop.getProperty("DEAFAULT_Y")),
				Double.parseDouble(prop.getProperty("DEAFAULT_HEADING")), 
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_PENDOWN")), 
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_TURTLE_VISIBILITY")),
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_TURTLE_ACTIVE"))
				);
		setPenColor(prop.getProperty("DEAFAULT_PEN_COLOR"));
		paneSize=new Dimension(height, width);
		initiateCanvas();
		initiateTurtle();
		container=new StackPane(lineCanvas, turtleContainer);
		setBackgroudColor(prop.getProperty("DEAFAULT_BACKGROUND_COLOR"));
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
	 * sets up the Canvas
	 */
	private void initiateCanvas() {
		lineCanvas=new Canvas(paneSize.getWidth(), paneSize.getHeight());
		GraphicsContext lineGC=lineCanvas.getGraphicsContext2D();
		lineGC.setFill(Color.TRANSPARENT);
		lineGC.fillRect(0,0,lineCanvas.getWidth(),lineCanvas.getHeight());
	}
	
	/**
	 * 
	 * @param color String specifying the color, USE VALID
	 */
	public void setBackgroudColor(String color){
		Color trueColor=myGUI.getColor(color);
		int blue=(int) (255*trueColor.getBlue()),
				red=(int) (255*trueColor.getRed()),
				green=(int) (255*trueColor.getGreen());
		container.setStyle(String.format("-fx-background-color: rgb(%d, %d, %d)", red, green, blue));
	}
	
	public void setPenColor(String color){
		penColor=myGUI.getColor(color);
	}
	
	public void setTurtleImg(File file){
		Image turtleImg=new Image(file.toURI().toString());
		turtle.setImage(turtleImg);
		moveTurtle(new TurtleParameters(0, -20,-25, 45, true, true, true));
	}
	
	/**
	 * sets up turtle and turtle container
	 */
	private void initiateTurtle() {
		Image turtleIMG =  new Image(getClass().getClassLoader().getResourceAsStream(prop.getProperty("deafaultTurtleImage")));
		turtle=new ImageView(turtleIMG); 
		turtle.setFitWidth(TURTLE_SIZE.getWidth());
		turtle.setFitHeight(TURTLE_SIZE.getHeight());
		turtleContainer=new Pane(turtle);
		standardizeSize(turtleContainer);
		moveTurtle(currentParams);
	}

	/**
	 * 
	 * API for updating visualization
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		Queue<TurtleParameters> paramQ=data.getTurtleParameters();
		while(paramQ.size()!=0){
			TurtleParameters newTurtleParams=paramQ.poll();
	    	if(newTurtleParams==null){
	    		return;
	    	}
	    	moveTurtle(newTurtleParams);
	    	currentParams=newTurtleParams;
		}
	}

	/**
	 * makes (0, 0) at the center of the display, 
	 * i.e. center-aligns every coordinate
	 */
	private double centralizeXPosition(double xPos){
		return xPos+paneSize.getWidth()/2-TURTLE_SIZE.getWidth()/2;
	}
	
	/**
	 * makes (0, 0) at the center of the display, 
	 * i.e. center-aligns every coordinate
	 */
	private double centralizeYPosition(double yPos){
		return yPos+paneSize.getHeight()/2-TURTLE_SIZE.getHeight()/2;
	}


    /**
     * Draws the turtle on the canvas
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param position specifies the position of the image's center point, and the heading of the image
     */
    private void moveTurtle(TurtleParameters position) {
    	turtle.setRotate(position.getHeading());
    	turtle.setX(centralizeXPosition(position.getX()));
    	turtle.setY(centralizeYPosition(position.getY()));
    	GraphicsContext canvasGC=lineCanvas.getGraphicsContext2D();
    	if(position.isPendown()){
    		canvasGC.setStroke(penColor);
            canvasGC.strokeLine(centralizeXPosition(currentParams.getX()), centralizeYPosition(currentParams.getY()), 
            		centralizeXPosition(position.getX())+TURTLE_SIZE.getWidth()/2, centralizeYPosition(position.getY())+TURTLE_SIZE.getHeight()/2);
    	}
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

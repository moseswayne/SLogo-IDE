package View.turtleDisplay;

import javafx.scene.paint.Color;
import java.awt.Dimension;
import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import utils.RawCommand;
import utils.TurtleParameters;

public class TurtleDisplay implements I_FrontEndModule {
	private Canvas lineCanvas;
	private Pane turtleContainer;
	private StackPane container;
	private ImageView turtle;
	private TurtleParameters currentPosition;
	private Paint penColor;
	private Dimension paneSize;
	private final Dimension turtleSize=new Dimension(20, 20);
	private final Paint DEAFAULT_PEN_COLOR=Color.BLACK;
	private final double DEAFAULT_X=0;
	private final double DEAFAULT_Y=0;
	private final double DEAFAULT_HEADING=0;
	private final boolean DEAFAULT_PENDOWN_POSITION=true;
	
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDisplay(int height, int width) {
		penColor=DEAFAULT_PEN_COLOR;
		currentPosition=new TurtleParameters(DEAFAULT_X, DEAFAULT_Y, DEAFAULT_HEADING, DEAFAULT_PENDOWN_POSITION);
		paneSize=new Dimension(height, width);
		initiateCanvas();
		initiateTurtle();
		container=new StackPane(lineCanvas, turtleContainer);
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
		lineGC.setFill(Color.GREY);
		lineGC.fillRect(0,0,lineCanvas.getWidth(),lineCanvas.getHeight());
	}
	
	/**
	 * sets up turtle and turtle container
	 */
	private void initiateTurtle() {
		Image turtleIMG =  new Image("http://www.mosaic.pro/images/products/detail/Turtletopviewlargenatural.JPG");
		turtle=new ImageView(turtleIMG); 
		turtle.setFitWidth(turtleSize.getWidth());
		turtle.setFitHeight(turtleSize.getHeight());
		turtleContainer=new Pane(turtle);
		standardizeSize(turtleContainer);
		moveTurtle(currentPosition);
	}

	/**
	 * 
	 * API for updating visualization
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
    	TurtleParameters newTurtleParams=data.getTurtleParameters();
    	if(newTurtleParams==null){
    		return;
    	}
		GraphicsContext canvasGC=lineCanvas.getGraphicsContext2D();
    	if(newTurtleParams.isPendown()){
    		canvasGC.setStroke(penColor);
            canvasGC.strokeLine(centralizeXPosition(currentPosition.getX()), centralizeYPosition(currentPosition.getY()), 
            		centralizeXPosition(newTurtleParams.getX())+turtleSize.getWidth()/2, centralizeYPosition(newTurtleParams.getY())+turtleSize.getHeight()/2);
    	}
    	moveTurtle(newTurtleParams);
    	currentPosition=newTurtleParams;
	}

	/**
	 * makes (0, 0) at the center of the display, 
	 * i.e. center-aligns every coordinate
	 */
	private double centralizeXPosition(double xPos){
		return xPos+paneSize.getWidth()/2-turtleSize.getWidth()/2;
	}
	
	/**
	 * makes (0, 0) at the center of the display, 
	 * i.e. center-aligns every coordinate
	 */
	private double centralizeYPosition(double yPos){
		return yPos+paneSize.getHeight()/2-turtleSize.getHeight()/2;
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

}

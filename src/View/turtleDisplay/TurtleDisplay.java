package View.turtleDisplay;

import javafx.scene.paint.Color;
import java.awt.Dimension;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Queue;

import View.ColorInterpreter;
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
	private TurtleParameters currentParams;
	private Paint penColor;
	private Dimension paneSize;

	
	private final Dimension TURTLE_SIZE=new Dimension(20, 20);
	private final Paint DEAFAULT_PEN_COLOR=Color.BLACK;
	private final String DEAFAULT_BACKGROUND_COLOR="white";
	private final double DEAFAULT_X=0;
	private final double DEAFAULT_Y=0;
	private final double DEAFAULT_HEADING=0;
	private final boolean DEAFAULT_PENDOWN_POSITION=true;
	private final boolean DEAFAULT_TURTLE_VISIBILITY=true;
	
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDisplay(int height, int width) {
		penColor=DEAFAULT_PEN_COLOR;
		currentParams=new TurtleParameters(DEAFAULT_X, DEAFAULT_Y, DEAFAULT_HEADING, 
				DEAFAULT_PENDOWN_POSITION, 
				DEAFAULT_TURTLE_VISIBILITY);
		paneSize=new Dimension(height, width);
		initiateCanvas();
		initiateTurtle();
		container=new StackPane(lineCanvas, turtleContainer);
		setBackgroudColor(DEAFAULT_BACKGROUND_COLOR);
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

	public void setTurtleImg(File file){
		Image turtleImg=new Image(file.toURI().toString());
		turtle=new ImageView(turtleImg);
		moveTurtle(new TurtleParameters(0, 25, 45, true, true));
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
		container.setStyle(String.format("-fx-background-color: %s", color.toLowerCase()));
	}
	
	public void setPenColor(String color){
		ColorInterpreter cInterpreter=new ColorInterpreter();
		float r=cInterpreter.getRValue(color.toLowerCase()),
				g=cInterpreter.getGValue(color.toLowerCase()),
				b=cInterpreter.getBValue(color.toLowerCase());
		penColor=Color.color(r, g, b);
	}
	
	
	/**
	 * sets up turtle and turtle container
	 */
	private void initiateTurtle() {
		Image turtleIMG =  new Image("http://www.mosaic.pro/images/products/detail/Turtletopviewlargenatural.JPG");
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
//    	turtle.setX(centralizeXPosition(currentParams.getX()+20));
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

}

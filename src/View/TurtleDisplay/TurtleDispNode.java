package View.TurtleDisplay;

import java.awt.Dimension;
import java.io.File;
import java.util.Properties;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import utils.PropertyUtility;
import utils.TurtleParameters;

public class TurtleDispNode {
	private Canvas lineCanvas;
	private Pane turtleContainer;
	private StackPane container;
	private FrontEndTurtle turtle;
	private TurtleParameters currentParams;
	private Dimension paneSize;
	private Properties prop;
	private final Dimension TURTLE_SIZE=new Dimension(20, 20);
	private Paint myPenColor;
	
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDispNode(int id, int height, int width) {
		prop=new PropertyUtility("deafaultTurtleDisp.properties").getProperties();
		currentParams=new TurtleParameters(id,Double.parseDouble(prop.getProperty("DEAFAULT_X")),
				Double.parseDouble(prop.getProperty("DEAFAULT_Y")),
				Double.parseDouble(prop.getProperty("DEAFAULT_HEADING")), 
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_PENDOWN")), 
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_TURTLE_VISIBILITY")),
				Boolean.parseBoolean(prop.getProperty("DEAFAULT_TURTLE_ACTIVE"))
				);
		paneSize=new Dimension(height, width);
		myPenColor=Color.BLACK;
		initiateCanvas();
		initiateTurtle(currentParams);
		container=new StackPane(lineCanvas, turtleContainer);
		container.setStyle("-fx-background-color: transparent");
		standardizeSize(container);
	}
	
	
	/**
	 * binds the size of a Region (namely container and turtleContainer in this case) 
	 * to the size of the "turtleDisplay"
	 * @param region
	 */
	private void standardizeSize(Region region) {
//		region.setPrefSize(paneSize.getWidth(), paneSize.getHeight());
		region.setMaxSize(paneSize.getWidth(), paneSize.getHeight());
//		region.setMinSize(paneSize.getWidth(), paneSize.getHeight());
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
	
	void setTurtleImg(File file){
		Image turtleImg=new Image(file.toURI().toString());
		turtle.setImage(turtleImg);
	}

	/**
	 * sets up turtle and turtle container
	 */
	private void initiateTurtle(TurtleParameters params) {
		Image turtleIMG =  new Image(getClass().getClassLoader().getResourceAsStream(prop.getProperty("deafaultTurtleImage")));
		turtle=new FrontEndTurtle(params); 
		turtle.setImage(turtleIMG);
		turtle.setFitWidth(TURTLE_SIZE.getWidth());
		turtle.setFitHeight(TURTLE_SIZE.getHeight());
		turtle.setOnDragEntered(new EventHandler<DragEvent>() {
		      public void handle(DragEvent event) {
		    	  System.out.println("entered drag");
		      }
		  });
		turtle.setOnDragExited(new EventHandler<DragEvent>() {
		      public void handle(DragEvent event) {
		    	  TurtleParameters newParams=new TurtleParameters(currentParams);
		    	  newParams.setX(event.getSceneX());
		    	  newParams.setX(event.getSceneY());
		    	  moveTurtle(newParams, myPenColor);
		      }
		  });
		turtleContainer=new Pane(turtle);
		turtleContainer.setMaxSize(paneSize.getWidth(), paneSize.getHeight());
		turtleContainer.setPrefSize(paneSize.getWidth(), paneSize.getHeight());
		turtleContainer.setMinSize(paneSize.getWidth(), paneSize.getHeight());
		standardizeSize(turtleContainer);
		moveTurtle(params, Color.TRANSPARENT);
	}
	
	void show(TurtleParameters param, Paint penColor){
		moveTurtle(param, penColor);
    	currentParams=param;
	}
    /**
     * Draws the turtle on the canvas
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param position specifies the position of the image's center point, and the heading of the image
     */
    private void moveTurtle(TurtleParameters position, Paint penColor) {
    	if (!myPenColor.equals(penColor)){
    		myPenColor=penColor;
    	}
    	turtle.setX(centralizeXPosition(position.getX()));
    	turtle.setY(centralizeYPosition(position.getY()));
    	turtle.setRotate(position.getHeading());
    	turtle.setVisible(position.turtleVisible());
    	
    	turtle.setParams(position);
    	GraphicsContext canvasGC=lineCanvas.getGraphicsContext2D();
    	if(position.isPendown()){
    		canvasGC.setStroke(penColor);
            canvasGC.strokeLine(centralizeXPosition(currentParams.getX())+TURTLE_SIZE.getWidth()/2, centralizeYPosition(currentParams.getY())+TURTLE_SIZE.getWidth()/2,
            		centralizeXPosition(position.getX())+TURTLE_SIZE.getWidth()/2, centralizeYPosition(position.getY())+TURTLE_SIZE.getWidth()/2);
    	}
    }


	private void animateTurtle(TurtleParameters position) {
		System.out.println("ANIMATING TURTLE");
		Path path=new Path();
    	path.getElements().add(new MoveTo(centralizeXPosition(position.getX()), centralizeYPosition(position.getY())));
    	PathTransition pathTransition = new PathTransition();
    	pathTransition.setDuration(Duration.millis(4000));
    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    	pathTransition.setPath(path);
    	pathTransition.setNode(turtle);
    	pathTransition.play();
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

	
	Node getContainerNode(){
		return container;
	}
	
	int getID(){
		return turtle.getID();
	}
	
	String getTurtleString(){
		return turtle.toString();
	}
}

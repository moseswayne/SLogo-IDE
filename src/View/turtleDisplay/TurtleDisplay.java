package View.turtleDisplay;

import javafx.scene.paint.Color;
import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import utils.RawCommand;
import utils.TurtlePosition;

public class TurtleDisplay implements I_FrontEndModule {
	private Canvas lineCanvas;
	private StackPane turtleContainer;
	private StackPane container;
	private ImageView turtle;
	private TurtlePosition currentPosition;
	private Paint penColor;
	private final Paint DEAFAULT_PEN_COLOR=Color.BLACK;
	private final double DEAFAULT_X=0;
	private final double DEAFAULT_Y=0;
	private final double DEAFAULT_HEADING=0;
	
	/**
	 *  
	 * @param height
	 * @param width
	 */
	public TurtleDisplay(int height, int width) {
		penColor=DEAFAULT_PEN_COLOR;
		currentPosition=new TurtlePosition(DEAFAULT_X, DEAFAULT_Y, DEAFAULT_HEADING);
		initiateCanvas(height, width);
		initiateTurtle();
		container=new StackPane(lineCanvas, turtleContainer);
		container.setMinSize(lineCanvas.getWidth(), lineCanvas.getHeight());
	}

	private void initiateTurtle() {
		Image turtleIMG =  new Image("http://forums.realgm.com/boards/images/nba/xtoronto_raptors.gif.pagespeed.ic.QSf6xmfYSP.png", 0, 0, true, true);
		turtle=new ImageView(turtleIMG); 
		turtleContainer=new StackPane(turtle);
		turtleContainer.setMaxSize(lineCanvas.getWidth(), lineCanvas.getHeight());
		drawTurtle(currentPosition);
	}

	private void initiateCanvas(int height, int width) {
		lineCanvas=new Canvas(height, width);
		GraphicsContext lineGC=lineCanvas.getGraphicsContext2D();
		lineGC.setFill(Color.GREY);
		lineGC.fillRect(0,0,lineCanvas.getWidth(),lineCanvas.getHeight());
	}

	/**
	 * 
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		TurtleDispData castedData=(TurtleDispData) data;
		draw(castedData);
	}
	
	private void draw(TurtleDispData data) {
    	GraphicsContext canvasGC=lineCanvas.getGraphicsContext2D();
    	TurtlePosition newPosition=(TurtlePosition) data.getData();
    	if(data.isPenDown()){
    		canvasGC.setStroke(penColor);
            canvasGC.strokeLine(currentPosition.getX(), currentPosition.getY(), newPosition.getX(), newPosition.getY());
    	}
    	drawTurtle(newPosition);
    	currentPosition=newPosition;
    }


    /**
     * Draws the turtle on the canvas
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param position specifies the position of the image's center point, and the heading of the image
     */
    private void drawTurtle(TurtlePosition position) {
    	turtle.setRotate(position.getHeading());
    	turtle.setX(position.getX());
    	turtle.setY(position.getY());
    }
    
    
	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getVisualizedContent() {
		container.getChildren().clear();
		container.getChildren().add(lineCanvas);
		container.getChildren().add(turtleContainer);
		return container;
	}

}

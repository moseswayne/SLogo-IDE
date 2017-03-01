package View.turtleDisplay;

import javafx.scene.paint.Color;
import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import utils.RawCommand;
import utils.TurtlePosition;

public class TurtleDisplay implements I_FrontEndModule {
	private Canvas canvas;
	private Image turtle;
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
		turtle =  new Image("http://forums.realgm.com/boards/images/nba/xtoronto_raptors.gif.pagespeed.ic.QSf6xmfYSP.png", 0, 0, true, true);//change this
		penColor=DEAFAULT_PEN_COLOR;
		currentPosition=new TurtlePosition(DEAFAULT_X, DEAFAULT_Y, DEAFAULT_HEADING);
		initiateCanvas(height, width);
	}

	private void initiateCanvas(int height, int width) {
		canvas=new Canvas(height, width);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		drawTurtle(currentPosition);
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
    	GraphicsContext gc=canvas.getGraphicsContext2D();
    	TurtlePosition newPosition=(TurtlePosition) data.getData();
    	if(data.isPenDown()){
    		gc.setStroke(penColor);
            gc.strokeLine(currentPosition.getX(), currentPosition.getY(), newPosition.getX(), newPosition.getY());
    	}
    	drawTurtle(newPosition);
    }

	/**
     * Rotates the GC
     *
     * @param gc
     * @param angle the angle of rotation.
     * @param px the x pivot coordinate for the rotation (in canvas coordinates).
     * @param py the y pivot coordinate for the rotation (in canvas coordinates).
     */
    private void rotateGC(double angle, double x, double y) {
    	GraphicsContext gc=canvas.getGraphicsContext2D();
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Draws the turtle on the canvas
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param position specifies the position of the image's center point, and the heading of the image
     */
    private void drawTurtle(TurtlePosition position) {
    	GraphicsContext gc=canvas.getGraphicsContext2D();
    	Double topLeftX=position.getX()-turtle.getWidth() / 2,
    			topLeftY=position.getY()- turtle.getHeight() / 2,
    			heading=position.getHeading();
        gc.save(); // saves the current state on stack, including the current transform
        rotateGC(heading, topLeftX , topLeftY);
        gc.drawImage(turtle, topLeftX, topLeftY);
        gc.restore(); // back to original state (before rotation)
    }
    
    
	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getVisualizedContent() {
		return canvas;
	}

}

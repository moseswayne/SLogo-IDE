package turtleOps;
import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;
import utils.TurtleParameters;

public class TurnLeft extends A_TurtleCommand{
private double degrees;
private static int DIRECTION = -1;
private static int FULL_CIRCLE = 360;
//corresponds to counter-clockwise
	@Override
	public TurtleParameters makeTurtleParameters(ParameterObject params) {
		myTurtle = params.getTurtle();
		degrees = returnValue(params);
		
		return new TurtleParameters(myTurtle.getX(), myTurtle.getY(), myTurtle.)
	}

	@Override
	public double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}
	private double newHeading(TurtleModel turtle, double degreeChange){
		double newHeading = turtle.getHeading() + degreeChange * DIRECTION;
		if(newHeading < 360){
			
		}
	}

}

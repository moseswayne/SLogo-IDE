package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;
import utils.TurtleParameters;

public class MoveBackwards extends A_TurtleCommand {
	private static int DIRECTION = -1;
	private double distance;
	private TurtleModel myTurtle;

	@Override
	public TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		distance = returnValue(params);
		myTurtle.newXCoordinate(distance, DIRECTION);
		myTurtle.newYCoordinate(distance, DIRECTION);
		return myTurtle;
	}

	@Override
	public double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}

}
package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

abstract class A_ManipulateTurtle extends A_TurtleCommand {
	protected TurtleModel updateTurtle(ParameterObject params) {
		TurtleModel turtle = params.getTurtle();
		turtle.setX(updateX(turtle.getX(), params));
		turtle.setY(updateY(turtle.getY(), params));
		return turtle;
	}

	abstract double updateX(Double oldX, ParameterObject params);

	abstract double updateY(Double oldY, ParameterObject params);

	protected double returnValue(ParameterObject params) {
		return params.getDoubleAt(0);
	}
}

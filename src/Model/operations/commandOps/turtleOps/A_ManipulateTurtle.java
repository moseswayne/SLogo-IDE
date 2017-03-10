package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

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
		return params.next();
	}
}

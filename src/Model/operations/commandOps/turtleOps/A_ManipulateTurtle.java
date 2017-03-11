package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

abstract class A_ManipulateTurtle extends A_TurtleCommand {
	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected double returnValue(ParameterObject params) {
		return params.next();
	}
}

package Model.operations.commandOps.turtleOps;

import Model.backEndUtils.ParameterObject;

/**
 * Corresponds to counterclockwise
 * 
 * @author Kris Elbert
 *
 */

public class TurnLeft extends A_TurnTurtle {

	@Override
	protected int setDirection() {
		return -1;
	}

	@Override
	double updateX(Double oldX, ParameterObject params) {
return oldX;
	}

	@Override
	double updateY(Double oldY, ParameterObject params) {
return oldY;
	}
}

package turtleOps;

import utils.ParameterObject;

/**
 * Rotates turtle clockwise
 * 
 * @author Elbert
 *
 */
public class TurnRight extends A_TurnTurtle {

	@Override
	protected int setDirection() {
		return 1;
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

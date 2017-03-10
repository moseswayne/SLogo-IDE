package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

public class SetHeading extends A_TurtleCommand {
	private TurtleModel myTurtle;
	private double previousHeading;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		previousHeading = myTurtle.getHeading();
		myTurtle.setHeading(params.getDoubleAt(0));
		return myTurtle;
	}

	/**
	 * Returns the number of degrees moved
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		double value;
		try {
			// if updateTurtle called before returnValue
			value = myTurtle.getHeading() - previousHeading;
		} catch (NullPointerException e) {
			myTurtle = params.getTurtle();
			value = params.getDoubleAt(0) - myTurtle.getHeading();
			// TODO or
			// updateTurtle(params);
			// value = this.returnValue(params);
		}
		return value;
	}
}

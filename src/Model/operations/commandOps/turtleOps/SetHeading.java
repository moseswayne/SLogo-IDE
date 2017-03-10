package Model.operations.commandOps.turtleOps;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

public class SetHeading extends A_TurtleCommand {
	private TurtleModel myTurtle;
	private double previousHeading;
	private double newHeading;

	@Override
	protected TurtleModel updateTurtle(ParameterObject params) {
		myTurtle = params.getTurtle();
		previousHeading = myTurtle.getHeading();
		newHeading = params.next();
		myTurtle.setHeading(newHeading);
		return myTurtle;
	}

	/**
	 * Returns the number of degrees moved Need to call update turtle before
	 * returnValue
	 */
	@Override
	protected double returnValue(ParameterObject params) {
		double value;
		try {
			// if updateTurtle called before returnValue
			value = myTurtle.getHeading() - previousHeading;
		} catch (NullPointerException e) {
			updateTurtle(params);
			value = returnValue(params);
		}
		return value;
	}
}

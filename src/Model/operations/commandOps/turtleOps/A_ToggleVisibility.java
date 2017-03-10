package Model.operations.commandOps.turtleOps;

import java.util.function.Function;

import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.A_TurtleCommand;

/**
 * Makes pen or turtle visible
 * Note: need to update the turtle before can return a value
 * @author Elbert
 *
 */
public abstract class A_ToggleVisibility extends A_TurtleCommand {
	private boolean RETURN;

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	public TurtleModel updateTurtleState(ParameterObject params, Function<TurtleModel, Boolean> operation) {
		TurtleModel myTurtle = params.getTurtle();
		RETURN = operation.apply(myTurtle);
		return myTurtle;
	}

	@Override
	protected double returnValue(ParameterObject params) {
		if (RETURN) {
			return 1;
		}
		return 0;
	}
}

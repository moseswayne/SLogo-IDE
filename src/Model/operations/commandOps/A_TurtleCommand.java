package Model.operations.commandOps;

import Model.TurtleModel;
import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;
import View.viewUtils.FrontEndData;
/**
 * Updates a turtle's state according to a command
 * @author Elbert
 *
 */
public abstract class A_TurtleCommand implements CommandOperation {

	public void execute(ParameterObject params, BackEndData data) {
		// Update turtle before get return value
		TurtleModel myTurtle = updateTurtle(params);
		data.setValue(returnValue(params));
		data.addTurtleParameters(myTurtle);
		// TODO may need to make a queue
	}

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected abstract double returnValue(ParameterObject params);

}

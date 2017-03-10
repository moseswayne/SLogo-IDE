package Operations;

import Model.BackEndData;
import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;
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

package Operations;

import Model.BackEndData;
import Model.TurtleModel;
import View.viewUtils.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleCommand implements CommandOperation {

	public void execute(ParameterObject params, BackEndData data) {
//Update turtle before get return value
		TurtleModel myTurtle = updateTurtle(params);
		data.setValue(returnValue(params));
		data.addTurtleParameters(myTurtle.getX(), myTurtle.getY(), myTurtle.getHeading(), myTurtle.getPenShowing(), myTurtle.getTurtleShowing());
		// TODO may need to make a queue
	}

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected abstract double returnValue(ParameterObject params);

}

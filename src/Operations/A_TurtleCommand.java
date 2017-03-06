package Operations;

import Model.BackEndData;
import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleCommand implements CommandOperation {

	public void execute(ParameterObject params, BackEndData data) {

		data.setValue(returnValue(params));
		TurtleModel myTurtle = updateTurtle(params);
		data.addTurtleParameters(myTurtle.getX(), myTurtle.getY(), myTurtle.getHeading(), myTurtle.getPenShowing(), myTurtle.getTurtleShowing());
		// TODO may need to make a queue
	}

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected abstract double returnValue(ParameterObject params);

}

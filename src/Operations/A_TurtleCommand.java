package Operations;

import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleCommand implements CommandOperation {

	public void execute(ParameterObject params, FrontEndData data) {

		data.setPrintData(returnValue(params));
		TurtleModel myTurtle = updateTurtle(params);
		data.addTurtleParameters(myTurtle.getX(), myTurtle.getY(), myTurtle.getHeading(), myTurtle.getPenShowing(), myTurtle.getTurtleShowing());
		// TODO may need to make a queue
	}

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected abstract double returnValue(ParameterObject params);

}

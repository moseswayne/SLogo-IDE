package Operations;

import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleCommand implements CommandOperation {

	public FrontEndData execute(ParameterObject params, FrontEndData data) {

		data.setPrintData(returnValue(params));
		TurtleModel myTurtle = updateTurtle(params);
		data.setTurtleParameters(myTurtle.getX(), myTurtle.getY(), myTurtle.getHeading(), myTurtle.getPenShowing());
		// TODO may need to make a queue
		return data;
	}

	protected abstract TurtleModel updateTurtle(ParameterObject params);

	protected abstract double returnValue(ParameterObject params);

}

package Operations;

import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleQuery implements CommandOperation {
	public FrontEndData execute(ParameterObject params, FrontEndData data) {
		data.setPrintData(returnQuery(params.getTurtle()));
		return data;
	}

	protected abstract double returnQuery(TurtleModel turtle);
}

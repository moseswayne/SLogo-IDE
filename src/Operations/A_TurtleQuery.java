package Operations;

import Model.BackEndData;
import Model.TurtleModel;
import View.viewUtils.FrontEndData;
import utils.ParameterObject;

public abstract class A_TurtleQuery implements CommandOperation {
	public void execute(ParameterObject params, BackEndData data) {
		data.setValue(returnQuery(params.getTurtle()));
	}

	protected abstract double returnQuery(TurtleModel turtle);
}

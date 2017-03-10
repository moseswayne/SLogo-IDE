package Model.operations.commandOps;

import Model.TurtleModel;
import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;

public abstract class A_TurtleQuery implements CommandOperation {
	public void execute(ParameterObject params, BackEndData data) {
		data.setValue(returnQuery(params.getTurtle()));
	}

	protected abstract double returnQuery(TurtleModel turtle);
}
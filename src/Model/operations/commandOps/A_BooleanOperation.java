package Model.operations.commandOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;
import View.viewUtils.FrontEndData;

public abstract class A_BooleanOperation implements CommandOperation {

	public void execute(ParameterObject params, BackEndData data) {
		if(evaluate(params)) {
			data.setValue(1);
		} else {
			data.setValue(0);
		}
	}
	
	protected abstract boolean evaluate(ParameterObject params);
}

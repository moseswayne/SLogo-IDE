package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;

public abstract class A_DisplayCommand implements CommandOperation {
	public void execute(ParameterObject params, BackEndData data) {
		data = modifyDisplay(params, data);
		data.setValue(returnValue(params));
	}

	protected abstract double returnValue(ParameterObject params);

	protected abstract BackEndData modifyDisplay(ParameterObject params, BackEndData data);
}

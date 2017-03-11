package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;

public abstract class A_DisplayCommand implements CommandOperation {
	BackEndSettings mySettings;
	//LOL NEED TO INITIALIZE TODO
	public void execute(ParameterObject params, BackEndData data) {
		//TODO returning a setting but it is not doing anything
		modifyDisplay(params, data, mySettings);
		data.setValue(returnValue(params, mySettings));
	}

	protected abstract double returnValue(ParameterObject params, BackEndSettings settings);

	protected abstract BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings);
}

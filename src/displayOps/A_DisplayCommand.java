package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;

public abstract class A_DisplayCommand implements CommandOperation {
	
	SLogoSettings mySettings;
	//LOL NEED TO INITIALIZE TODO
	public void execute(ParameterObject params, BackEndData data) {
		//TODO returning a setting but it is not doing anything
		modifyDisplay(params, data, mySettings);
		data.setValue(returnValue(params, mySettings));
	}

	protected abstract double returnValue(ParameterObject params, SLogoSettings settings);

	protected abstract void modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings);

}

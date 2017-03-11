package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;

public class getPenIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, SLogoSettings settings) {
		return (double) settings.getPen();
	}

	@Override
	protected void modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings) {

	}
}

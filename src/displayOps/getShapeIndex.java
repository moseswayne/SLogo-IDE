package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;

public class getShapeIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, SLogoSettings settings) {
		return (double) settings.getShapeIndex();
	}

	@Override
	protected SLogoSettings modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings) {
		return settings;
	}
}

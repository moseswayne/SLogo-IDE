package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;

public class SetPalette extends A_DisplayCommand{
	private int toReturn;
	@Override
	protected double returnValue(ParameterObject params, SLogoSettings settings) {
		return (double) toReturn;
	}

	@Override
	protected SLogoSettings modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings) {
		toReturn = settings.addColor(params);
		return settings;
	}
}

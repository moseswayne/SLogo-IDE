package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;

public class SetPenSize extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, SLogoSettings settings) {
		return (double) settings.getPenSize();
	}

	@Override
	protected SLogoSettings modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings) {
		settings.setPenSize(params.next().intValue());
		return settings;
	}
}

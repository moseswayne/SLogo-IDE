package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.backEndUtils.ParameterObject;

public class SetShapeIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, SLogoSettings settings) {
		return (double) settings.getShapeIndex();
	}

	@Override
	protected SLogoSettings modifyDisplay(ParameterObject params, BackEndData data, SLogoSettings settings) {
		settings.setShape(params.next().intValue());
		return settings;
	}
}

package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;

public class SetPalette extends A_DisplayCommand{
	private int toReturn;
	@Override
	protected double returnValue(ParameterObject params, BackEndSettings settings) {
		return (double) toReturn;
	}

	@Override
	protected BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings) {
		toReturn = settings.addColor(params);
		return settings;
	}
}

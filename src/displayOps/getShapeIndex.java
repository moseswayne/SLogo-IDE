package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;

public class getShapeIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, BackEndSettings settings) {
		return (double) settings.getShapeIndex();
	}

	@Override
	protected BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings) {
		return settings;
	}
}

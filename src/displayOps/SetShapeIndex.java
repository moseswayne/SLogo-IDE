package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;

public class SetShapeIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, BackEndSettings settings) {
		return (double) settings.getShapeIndex();
	}

	@Override
	protected BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings) {
		settings.setShape(params.next().intValue());
		return settings;
	}
}

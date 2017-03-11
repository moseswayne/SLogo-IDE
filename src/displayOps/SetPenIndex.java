package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;

public class SetPenIndex extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, BackEndSettings settings) {
		return (double) settings.getPen();
	}

	@Override
	protected BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings) {
		settings.setPen(params.next().intValue());
		return settings;
	}
}

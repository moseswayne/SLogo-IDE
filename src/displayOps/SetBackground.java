package displayOps;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.BackEndSettings;
import Model.backEndUtils.ParameterObject;

public class SetBackground extends A_DisplayCommand{

	@Override
	protected double returnValue(ParameterObject params, BackEndSettings settings) {
		return (double) settings.getBackground();
	}

	@Override
	protected BackEndSettings modifyDisplay(ParameterObject params, BackEndData data, BackEndSettings settings) {
		settings.setBackground(params.next().intValue());
		return settings;
	}

}

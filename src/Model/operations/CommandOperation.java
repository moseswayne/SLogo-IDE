package Model.operations;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;

public interface CommandOperation {
	public void execute(ParameterObject params, BackEndData data);
}

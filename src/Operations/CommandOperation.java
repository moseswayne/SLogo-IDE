package Operations;

import Model.BackEndData;
import utils.ParameterObject;

public interface CommandOperation {
	public void execute(ParameterObject params, BackEndData data);
}

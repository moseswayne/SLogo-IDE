package Operations;

import View.FrontEndData;
import utils.ParameterObject;

public interface CommandOperation {
	public void execute(ParameterObject params, FrontEndData data);
}

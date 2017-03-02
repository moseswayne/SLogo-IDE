package Operations;

import View.FrontEndData;
import utils.ParameterObject;

public interface CommandOperation {
	public FrontEndData execute(ParameterObject params, FrontEndData data);
}

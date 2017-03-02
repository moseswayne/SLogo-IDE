package Operations;

import Model.TurtleModel;
import View.FrontEndData;
import utils.ParameterObject;

public interface CommandOperation {
	public FrontEndData execute(ParameterObject params, FrontEndData data);
}

package Operations;

import View.FrontEndData;
import utils.TurtleModel;

public interface CommandOperation {
	public ParameterObject execute(ParameterObject params);

	public FrontEndData execute();
}

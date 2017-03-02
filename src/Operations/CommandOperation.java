package Operations;

import Model.TurtleModel;
import View.FrontEndData;

public interface CommandOperation {
	public ParameterObject execute(ParameterObject params);

	public FrontEndData execute();
}

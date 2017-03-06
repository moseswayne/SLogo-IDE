package Operations;

import Model.BackEndData;
import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_BooleanOperation implements CommandOperation {

	public void execute(ParameterObject params, BackEndData data) {
		if(evaluate(params)) {
			data.setValue(1);
		} else {
			data.setValue(0);
		}
	}
	
	public abstract boolean evaluate(ParameterObject params);
}

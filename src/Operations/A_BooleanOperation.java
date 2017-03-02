package Operations;

import View.FrontEndData;
import utils.ParameterObject;

public abstract class A_BooleanOperation implements CommandOperation {

	public void execute(ParameterObject params, FrontEndData data) {
		if(evaluate(params)) {
			data.setPrintData(1);
		} else {
			data.setPrintData(0);
		}
	}
	
	public abstract boolean evaluate(ParameterObject params);
}

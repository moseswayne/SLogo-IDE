package turtleOps;

import Model.TurtleModel;
import Operations.A_TurtleCommand;
import utils.ParameterObject;

abstract class A_ManipulateTurtle extends A_TurtleCommand{
	protected abstract TurtleModel updateTurtle(ParameterObject params);
	
	protected double returnValue(ParameterObject params){
		return params.getDoubleAt(0);
	}
}

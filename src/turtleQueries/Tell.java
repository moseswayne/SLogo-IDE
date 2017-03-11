package turtleQueries;

import java.util.List;

import Model.TurtleManager;
import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.turtleOps.A_MultipleTurtles;

/**
 * Gets list of TurtleModels according to their ID number
 * 
 * @author Kris Elbert
 *
 */
public class Tell extends A_MultipleTurtles {

	@Override
	protected List<TurtleModel> getTurtles(TurtleManager manager, ParameterObject params) {
		return manager.getTheseActiveTurtles(params);
	}

}

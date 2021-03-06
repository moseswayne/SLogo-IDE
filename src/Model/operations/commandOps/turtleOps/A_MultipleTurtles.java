package Model.operations.commandOps.turtleOps;

import java.util.List;
import Model.TurtleManager;
import Model.TurtleModel;
import Model.backEndUtils.ParameterObject;

/**
 * Gets TurtleModels based on conditions
 * 
 * @author Kris Elbert
 *
 */
public abstract class A_MultipleTurtles {

	protected abstract List<TurtleModel> getTurtles(TurtleManager manager, ParameterObject params);

}

import java.util.ArrayList;
import java.util.List;

import Model.TurtleManager;
import Model.TurtleModel;
import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.turtleOps.A_MultipleTurtles;

public class Ask extends A_MultipleTurtles{
	List<TurtleModel> myTurtles;
	@Override
	protected List<TurtleModel> getTurtles(TurtleManager manager, ParameterObject params) {
		myTurtles = manager.getTheseTempTurtles(params);
		
	}

}

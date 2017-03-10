import java.util.ArrayList;
import java.util.List;

import Model.BackEndData;
import Model.TurtleManager;
import Model.TurtleModel;
import utils.ParameterObject;

public class Ask extends A_MultipleTurtles{
	List<TurtleModel> myTurtles;
	@Override
	protected List<TurtleModel> getTurtles(TurtleManager manager, ParameterObject params) {
		myTurtles = manager.getTheseTempTurtles(params);
		
	}

}

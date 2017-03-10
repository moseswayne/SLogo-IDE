package turtleQueries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.TurtleManager;
import Model.TurtleModel;
import utils.ParameterObject;

/**
 * Gets list of TurtleModels according to their ID number
 * 
 * @author Kris Elbert
 *
 */
public class Tell {
	public List<TurtleModel> getTheseTurtles(ParameterObject params, TurtleManager manager) {
		List<TurtleModel> retTurtles = new ArrayList<TurtleModel>();
		Iterator<Double> paramsIter = params.iterator();
		while (paramsIter.hasNext()) {
			retTurtles.add(manager.getTurtle(paramsIter.next().intValue()));
		}
		return retTurtles;
	}
}

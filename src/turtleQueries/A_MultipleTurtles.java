package turtleQueries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.TurtleManager;
import Model.TurtleModel;
import Operations.A_TurtleQuery;
import utils.ParameterObject;
/**
 * Gets TurtleModels based on conditions
 * @author Kris Elbert
 *
 */
public abstract class A_MultipleTurtles {

	public List<TurtleModel> check(Iterable<TurtleModel> manager, I_CheckTurtleConditions checker) {
		Iterator<TurtleModel> turtleIter = manager.iterator();
		List<TurtleModel> subsection = new ArrayList<TurtleModel>();
		while (turtleIter.hasNext()) {
			TurtleModel newTurtle = turtleIter.next();
			if (checker.check(newTurtle)) {
				subsection.add(newTurtle);
			}
		}
		return subsection;
	}
	public List<TurtleModel> getTheseTurtles(TurtleManager manager, Iterable<Double> params) {
		List<TurtleModel> retTurtles = new ArrayList<TurtleModel>();
		Iterator<Double> paramsIter = params.iterator();
		while (paramsIter.hasNext()) {
			retTurtles.add(manager.getTurtle(paramsIter.next().intValue()));
		}
		return retTurtles;
	}
	protected abstract void reset();
	
}

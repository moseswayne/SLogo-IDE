package turtleQueries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.TurtleModel;
import Operations.A_TurtleQuery;
/**
 * Gets TurtleModels based on conditions
 * @author Kris Elbert
 *
 */
public abstract class A_MultipleTurtles extends A_TurtleQuery {

	protected static List<TurtleModel> check(Iterable<TurtleModel> manager, I_CheckTurtleConditions checker) {
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
}

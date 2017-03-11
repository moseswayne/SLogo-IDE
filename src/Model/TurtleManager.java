package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import Model.backEndUtils.ParameterObject;
import Model.operations.commandOps.turtleOps.I_CheckTurtleConditions;

/**
 * Manages multiple turtles Makes and returns TurtleModels according to
 * specifications
 * 
 * @author Kris Elbert
 *
 */
public class TurtleManager implements Iterable<TurtleModel> {
	private List<TurtleModel> myTurtleList = new ArrayList<TurtleModel>();
	private List<TurtleModel> myActiveTurtleList = new ArrayList<TurtleModel>();
	private static double HOMEX = 0, HOMEY = 0;
	private TurtleModel myActiveTurtle;

	/**
	 * Adds a turtle to the list at a home position Returns this new TurtleModel
	 */
	public TurtleModel makeTurtle() {
		return makeTurtle(HOMEX, HOMEY);
	}

	/**
	 * Creates a turtle at the specified location and adds it to the list Note
	 * that ID numbers start at 1, not 0.
	 * 
	 * @param x
	 * @param y
	 * @return this new turtle
	 */
	public TurtleModel makeTurtle(double x, double y) {
		TurtleModel newTurtle = new TurtleModel(x, y, this.getTurtleCount() + 1);
		myTurtleList.add(newTurtle);
		myActiveTurtle = newTurtle;
		return newTurtle;
	}

	/**
	 * Adds enough turtles so that there are this many total turtles in the list
	 * 
	 * @param numberOfTurtles
	 */
	public void makeTurtles(int numberOfTurtles) {
		for (int i = this.getTurtleCount() + 1; i <= numberOfTurtles; i++) {
			makeTurtle();
		}
	}

	/**
	 * Returns a TurtleModel of the specified ID number
	 * 
	 * @param idNumber
	 * @return TurtleModel
	 */
	public TurtleModel getTurtle(int idNumber) {
		try {
			TurtleModel turtle = myTurtleList.get(idNumber);
			myActiveTurtleList.add(turtle);
			return turtle;
		} catch (NullPointerException e) {
			return makeTurtle();
		}
	}

	/**
	 * Gets a list of certain turtles that match the given ID numbers Note: does
	 * not reset the active turtle or reset the active list. Useful for Ask
	 * 
	 * @param idNumbers
	 *            that can iterate through
	 * @return list of TurtleModels
	 */
	public List<TurtleModel> getTheseTempTurtles(ParameterObject params) {
		List<TurtleModel> retTurtles = new ArrayList<TurtleModel>();
		while (params.hasNext()) {
			retTurtles.add(this.getTurtle(params.next().intValue()));
		}
		return retTurtles;
	}

	/**
	 * Gets a list of certain turtles and makes this list active. Useful for
	 * Tell
	 * 
	 * @param params
	 *            so can iterate through ID numbers
	 * @return Active list of TurtleModels
	 */
	public List<TurtleModel> getTheseActiveTurtles(ParameterObject params) {
		myActiveTurtleList.clear();
		myActiveTurtleList = getTheseTempTurtles(params);
		return getActiveTurtles();
	}

	/**
	 * Gets a list of turtleModels corresponding to a specific condition Can use
	 * with ask or tell depending on check
	 * 
	 * @param checker
	 * @return
	 */
	public List<TurtleModel> getTheseTurtles(I_CheckTurtleConditions checker,
			Function<Iterable<Double>, List<TurtleModel>> operation) {
		Iterable<Double> idNumbers = new ArrayList<Double>();
		for (TurtleModel t : myTurtleList) {
			// sorry for the casting
			if (checker.check(t)) {
				((ArrayList<Double>) idNumbers).add((double) t.getID());
			}
		}
		return operation.apply(idNumbers);
	}

	/**
	 * Return the entire list of turtles
	 * 
	 * @return List<TurtleModel>
	 */
	public List<TurtleModel> getAllTurtles() {
		return myTurtleList;
	}

	/**
	 * @return int total number of turtles in the list
	 */
	public int getTurtleCount() {
		return myTurtleList.size();
	}

	/**
	 * Returns the list of active turtles
	 * 
	 * @return list of TurtleModels
	 */
	public List<TurtleModel> getActiveTurtles() {
		if (myActiveTurtleList.isEmpty()) {
			myActiveTurtleList.add(myActiveTurtle);
		}
		return myActiveTurtleList;
	}

	/**
	 * Returns the singular active turtle
	 * 
	 * @return TurtleModel
	 */
	public TurtleModel getActiveTurtle() {
		return myActiveTurtle;
	}

	/**
	 * Returns the ID of the active turtle
	 * 
	 * @return double
	 */
	public double getActiveTurtleID() {
		return myActiveTurtle.getID();
	}

	/**
	 * Sets a new turtle to be active
	 * 
	 * @param idNumber
	 *            of the new active turtle
	 * @return this newly activated turtle
	 */
	public TurtleModel setActiveTurtle(int idNumber) {
		myActiveTurtle = getTurtle(idNumber);
		return getActiveTurtle();
	}

	/**
	 * Can also set an active turtle through its turtleModel
	 * 
	 * @param turtle
	 * @return that same turtleModel
	 */
	public TurtleModel setActiveTurtle(TurtleModel turtle) {
		return setActiveTurtle(turtle.getID());
	}

	/**
	 * Creates an iterator over all of the turtles in the list
	 */
	@Override
	public Iterator<TurtleModel> iterator() {
		return this.iterator(myTurtleList);
	}

	/**
	 * Iterates over a subsection of turtles
	 * 
	 * @param List
	 *            turtleList
	 * @return iterator over this list
	 */
	public Iterator<TurtleModel> iterator(List<TurtleModel> turtleList) {
		return new TurtleIterator(turtleList, this);
	}

	/**
	 * Creates an iterator through the list of turtles updates the active turtle
	 * each time is iterated through
	 */
	protected class TurtleIterator implements Iterator<TurtleModel> {
		private List<TurtleModel> myList;
		private int currentPosition;
		private TurtleManager myTurtleManager;

		/**
		 * Iterates through a specific list of turtles
		 * 
		 * @param theseTurtles
		 *            is the specific subsection, or whole
		 * @param TurtleManager
		 *            that holds all turtles
		 */
		protected TurtleIterator(List<TurtleModel> theseTurtles, TurtleManager man) {
			myList = new ArrayList<TurtleModel>(theseTurtles);
			myTurtleManager = man;
			reset();
		}

		/**
		 * Returns the next TurtleModel in the list Sets this turtle as the
		 * active turtle
		 * 
		 * @return
		 */
		// will loop around if list is too small
		@Override
		public TurtleModel next() {
			TurtleModel nextTurtle = myList.get(currentPosition % myList.size());
			myTurtleManager.setActiveTurtle(nextTurtle);
			currentPosition += 1;
			return nextTurtle;
		}

		/**
		 * Returns true if there is a subsequent TurtleModel in the list
		 * 
		 * @return true if there is another TurtleModel
		 */
		@Override
		public boolean hasNext() {
			return currentPosition < myList.size();
		}

		/**
		 * Resets position and active turtle back to the beginning of the
		 * specific list
		 */
		public void reset() {
			currentPosition = 0;
			myTurtleManager.setActiveTurtle(myList.get(0));
		}
	};
}

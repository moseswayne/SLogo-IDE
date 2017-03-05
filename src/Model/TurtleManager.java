package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages multiple turtles
 * 
 * @author Elbert
 *
 */
public class TurtleManager implements Iterable<TurtleModel> {
	private List<TurtleModel> myTurtleList;
	private static double HOMEX = 0, HOMEY = 0;
	private int myActiveTurtleID;

	/**
	 * Adds a turtle to the list at a home position Returns this new TurtleModel
	 */
	public TurtleModel makeTurtle() {
		return makeTurtle(HOMEX, HOMEY);
	}

	/**
	 * Creates a turtle at the specified location and adds it to the list
	 * 
	 * @param x
	 * @param y
	 * @return this new turtle
	 */
	public TurtleModel makeTurtle(double x, double y) {
		if (myTurtleList == null) {
			myTurtleList = new ArrayList<TurtleModel>();
		}
		TurtleModel newTurtle = new TurtleModel(x, y, this.getTurtleCount() + 1);
		myTurtleList.add(newTurtle);
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
	 * @return int total number of turtles in the list
	 */
	public int getTurtleCount() {
		return myTurtleList.size();
	}

	/**
	 * Returns a TurtleModel of the specified ID number
	 * 
	 * @param idNumber
	 * @return TurtleModel
	 */
	public TurtleModel getTurtle(int idNumber) {
		try {
			return myTurtleList.get(idNumber);
		} catch (NullPointerException e) {
			// TODO create turtleNotFound exception
			return null;
		}
	}

	/**
	 * Gets a list of certain turtles that match the given ID numbers
	 * 
	 * @param idNumbers
	 * @return list of TurtleModels
	 */
	public List<TurtleModel> getTheseTurtles(List<Integer> idNumbers) {
		List<TurtleModel> subsection = new ArrayList<TurtleModel>();
		for (int i : idNumbers) {
			TurtleModel thisTurtle;
			try {
				thisTurtle = myTurtleList.get(i);
			} catch (NullPointerException e) {
				thisTurtle = makeTurtle();
			}
			subsection.add(thisTurtle);
		}
		return subsection;
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
	 * Returns the singular active turtle
	 * 
	 * @return TurtleModel
	 */
	public TurtleModel getActiveTurtle() {
		return getTurtle(myActiveTurtleID);
	}

	/**
	 * Sets a new turtle to be active
	 * 
	 * @param idNumber
	 *            of the new active turtle
	 * @return this newly activated turtle
	 */
	public TurtleModel setActiveTurtle(int idNumber) {
		myActiveTurtleID = idNumber;
		return getActiveTurtle();
	}

	/**
	 * Allows iteration over the list of turtles
	 */
private class TurtleListIterator implements Iterator<TurtleModel>{
	private List<TurtleModel> myList;
	private int currentPosition;
		return new Iterator<TurtleModel>() {
			private  Iterator<TurtleModel> turtleIter = iterator();
public void overSubsection(List<TurtleModel> theseTurtles){
	turtleIter = theseTurtles.iterator();
}
			@Override
			public TurtleModel next() {
				return setActiveTurtle(turtleIter.next().getID());
			}

			@Override
			public boolean hasNext() {
				return turtleIter.hasNext();
			}
		};
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TurtleModel next() {
		// TODO Auto-generated method stub
		return null;
	}
}

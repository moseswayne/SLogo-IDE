package tree;

import java.util.List;
import java.util.Map;

import Model.BackEndData;
import Model.TurtleManager;
import Model.TurtleModel;
import Operations.A_MultipleTurtles;
import Operations.A_StructureCommand;
import Operations.CommandOperation;
import utils.ParameterObject;

/**
 * Get and set turtles in backend data that operating on then perform operations
 * iterating through them
 * 
 * @author Elbert
 *
 */
public class TurtleCommandNode extends A_StructureCommand implements ExpressionNode {
	private TurtleManager myTurtleManager;
	private A_MultipleTurtles myOperation;
	private List<TurtleModel> myTurtleList;

	public TurtleCommandNode() {
		myTurtleManager = new TurtleManager();
	}

	protected List<TurtleModel> getTurtleList() {
		myTurtles = myOperation.execute(myTurtleManager);
		for (TurtleModel t : myTurtles) {
			data.addTurtleParameters(t);
		}
	}

	public BackEndData setTurtles() {
		BackEndData bed = new BackEndData();
		for (TurtleModel t : myOperation.execute(myTurtleManager)) {
			bed.addTurtleParameters(t);
		}
		return bed;
	}

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionNode cloneNode() {
		new TurtleCommandNode = 
	}

	@Override
	public void modifyInstructionStack(ParameterObject params) {
		for (TurtleModel t : myTurtleList) {
			params.addListStack();
		}

	}

}

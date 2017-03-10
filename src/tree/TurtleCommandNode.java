package tree;

import java.util.Map;

import Model.BackEndData;
import Model.TurtleManager;
import Model.TurtleModel;
import utils.ParameterObject;

public class TurtleCommandNode extends CommandNode{
	
	TurtleManager myManager;

	public TurtleCommandNode(TurtleManager manager) {
		super();
		myManager = manager;
	}

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap) {
		for(TurtleModel turtle:myManager.getActiveTurtles()) {
			getOp().execute(getTurtleParameters(startData, varMap,turtle), startData);
		}
		System.out.println(startData.getMyValue().toString());
		return startData.getMyValue().toString();
	}

	@Override
	public ExpressionNode cloneNode() {
		TurtleCommandNode thisNode = new TurtleCommandNode(myManager);
		cloneContents(thisNode);
		return thisNode;
	}
	
	private ParameterObject getTurtleParameters(BackEndData data, Map<String, Double> vars, TurtleModel turtle) {
		ParameterObject turtParameters = getParameters(data, vars);
		turtParameters.setTurtle(turtle);
		return turtParameters;
	}
}

package Model.expressionTree.commandNode;

import java.util.List;
import java.util.Map;

import Model.TurtleManager;
import Model.TurtleModel;
import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.backEndUtils.SLogoSettings;
import Model.expressionTree.ExpressionNode;

public class TurtleCommandNode extends CommandNode {
/*
 * Need to get turtles from turtlemanager
 * need to iterate through them, adding parameters to the parameter object as make
 */
	TurtleManager myManager;

	public TurtleCommandNode(TurtleManager manager) {
		super();
		myManager = manager;
	}
	private List<TurtleModel> getTurtles(){
		return myManager.getActiveTurtles();
	}

	@Override
	public String getValue(BackEndData startData, Map<String, Double> varMap, SLogoSettings settings) {
		for (TurtleModel turtle : myManager.getActiveTurtles()) {//make for specific ask or tell commands
			//default to whatever the current active turtle is
			getOp().execute(getTurtleParameters(startData, varMap, settings), startData);
		}
		return startData.getMyValue().toString();
	}

	@Override
	public ExpressionNode cloneNode() {
		TurtleCommandNode thisNode = new TurtleCommandNode(myManager);
		cloneContents(thisNode);
		return thisNode;
	}

	private ParameterObject getTurtleParameters(BackEndData data, Map<String, Double> vars, SLogoSettings settings) {
		ParameterObject turtParameters = getParameters(data, vars, settings);
		for (TurtleModel t: this.getTurtles()){
		turtParameters.setTurtle(t);
	}
		return turtParameters;
	}
}
